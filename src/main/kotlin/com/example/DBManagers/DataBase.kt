package com.example.DBManagers

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.FindIterable
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import org.bson.Document
import org.bson.conversions.Bson
import org.bson.types.ObjectId
import org.litote.kmongo.findOneById

object DataBase {
    private val client: MongoClient
    private val database: MongoDatabase

    init {
        val connectionString = "mongodb+srv://vlanuka04:l6uWrQuiRSZxaB15@cluster0.rpjesgn.mongodb.net/?retryWrites=true&w=majority"
        val clientSettings = MongoClientSettings.builder().applyConnectionString(ConnectionString(connectionString)).build()
        client = MongoClients.create(clientSettings)
        database = client.getDatabase("users-db")
    }

    fun insert(collectionName: String, document: Document):String {
        val collection = database.getCollection(collectionName)
        collection.insertOne(document)
        return document["_id"].toString()
    }

    fun query(collectionName: String, filter: Bson): List<Document> {
        val collection = database.getCollection(collectionName)
        val cursor = collection.find(filter)
        val result = mutableListOf<Document>()
        for (doc in cursor) {
            result.add(doc)
        }
        return result
    }

    fun update(collectionName: String, document: Document) {
        val collection = database.getCollection(collectionName)
        collection.replaceOne(eq("_id",document["_id"]),document)
    }

    fun delete(collectionName: String, id:String) {
        val collection = database.getCollection(collectionName)
        collection.deleteOne(collection.findOneById(ObjectId(id)))
    }

    fun get(collectionName: String, id: String): Document?{
        val collection = database.getCollection(collectionName)
        return collection.findOneById(ObjectId(id))
    }


    fun getFieldValue(collectionName: String, id: String, field: String):Any?{
        val collection = database.getCollection(collectionName)
        val usersDoc = collection.findOneById(id)
        if (usersDoc !=null) return usersDoc[field]
        else return null
    }

    fun getItemByField(collectionName: String, field:String, id:String): FindIterable<Document> {
        val collection = database.getCollection(collectionName)
        return collection.find(eq(field, id))
    }

    fun isExist(collectionName: String, id:String):Boolean{ //true if exists
        val collection= database.getCollection(collectionName)
        return collection.findOneById(id)!=null
    }

    fun isNameFieldExist(collectionName: String, field:String, fieldValue:String):Boolean{ //true uf exists
        val collection= database.getCollection(collectionName)
        return collection.find(eq(field, fieldValue)).first()!=null
    }

    fun close() {
        client.close()
    }
}
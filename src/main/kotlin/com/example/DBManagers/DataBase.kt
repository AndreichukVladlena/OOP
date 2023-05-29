package com.example.DBManagers

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.*
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates
import org.bson.Document
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

    fun get(collectionName: String, id: String):Document?{
        val collection = database.getCollection(collectionName)
        val query = Document("_id", ObjectId(id))
        return collection.find(query).first()
    }

    fun itemByIdExists(collectionName: String, id: String):Boolean{
        val collection = database.getCollection(collectionName)
        return collection.findOneById(ObjectId(id))!=null
    }

    fun getByFieldValue(collectionName: String, field: String, value: Any):Document?{
        val collection = database.getCollection(collectionName)
        val query = Document(field, value)
        return collection.find(query).first()
    }

    fun getSeveralByFieldValue(collectionName: String, field: String, value: Any): FindIterable<Document> {
        val collection = database.getCollection(collectionName)
        val query = Document(field, value)
        return collection.find(query)
    }

    fun getFieldValue(collectionName: String, id:String, field: String):Any?{
        val collection = database.getCollection(collectionName)
        val currDoc = collection.findOneById(ObjectId(id))
        if (currDoc!=null && currDoc.containsKey(field)) return currDoc[field]
        else return null
    }

    fun update(collectionName: String, id: String, field: String, value: Any){
        val collection = database.getCollection(collectionName)
        collection.updateOne(eq("_id", ObjectId(id)), Updates.set(field, value))
    }
    fun delete(collectionName: String, id:String) {
        val collection = database.getCollection(collectionName)
        collection.deleteOne(collection.findOneById(ObjectId(id)))
    }

    fun getDB(collectionName: String): MongoCollection<Document> {
        return database.getCollection(collectionName)
    }

//    fun get(collectionName: String, id: String): Document?{
//        val collection = database.getCollection(collectionName)
//        return collection.findOneById(ObjectId(id))
//    }


//    fun getFieldValue(collectionName: String, id: String, field: String):Any?{
//        val collection = database.getCollection(collectionName)
//        val usersDoc = collection.findOneById(ObjectId(id))
//        if (usersDoc !=null) return usersDoc[field]
//        else return null
//    }

//    fun getItemIdByField(collectionName: String, field:String, value:String): String? {
//        val collection = database.getCollection(collectionName)
//        return collection.findOne(eq(field, value))?.get("_id").toString()
//    }
//
//    fun getItemsByField(collectionName: String, field:String, value:Any): FindIterable<Document> {
//        val collection = database.getCollection(collectionName)
//        return collection.find(eq(field, value))
//    }
//
//    fun isExist(collectionName: String, id:String):Boolean{ //true if exists
//        val collection= database.getCollection(collectionName)
//        return collection.findOneById(ObjectId(id))!=null
//    }
//
//    fun isNameFieldExist(collectionName: String, field:String, fieldValue:String):Boolean{ //true uf exists
//        val collection= database.getCollection(collectionName)
//        return collection.find(eq(field, fieldValue)).first()!=null
//    }
//
//    fun replaceDoc(collectionName: String,id: String, document: Document){
//        val collection = database.getCollection(collectionName)
//        collection.replaceOne(collection.findOneById(ObjectId(id)), document)
//    }

    fun close() {
        client.close()
    }
}
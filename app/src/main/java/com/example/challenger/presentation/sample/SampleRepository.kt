package com.example.challenger.presentation.sample

import com.example.challenger.entities.AsyncResult
import com.example.challenger.entities.Sample
import com.example.challenger.entities.Table
import com.example.challenger.extensions.getData
import com.example.challenger.extensions.postData
import com.google.firebase.database.DatabaseReference

class SampleRepository(private val firebase: DatabaseReference) {

    fun postSample(sampleObject: Sample) {
        firebase.postData(Table.SAMPLE, sampleObject)
    }

    suspend fun getSamples(): AsyncResult<List<Sample>> {
        return firebase.getData(Table.SAMPLE)
    }
}



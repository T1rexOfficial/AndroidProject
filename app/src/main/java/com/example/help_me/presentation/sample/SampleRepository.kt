package com.example.help_me.presentation.sample

import com.example.help_me.entities.AsyncResult
import com.example.help_me.entities.Sample
import com.example.help_me.entities.Table
import com.example.help_me.extensions.getData
import com.example.help_me.extensions.postData
import com.google.firebase.database.DatabaseReference

class SampleRepository(private val firebase: DatabaseReference) {

    fun postSample(sampleObject: Sample) {
        firebase.postData(Table.SAMPLE, sampleObject)
    }

    suspend fun getSamples(): AsyncResult<List<Sample>> {
        return firebase.getData(Table.SAMPLE)
    }
}



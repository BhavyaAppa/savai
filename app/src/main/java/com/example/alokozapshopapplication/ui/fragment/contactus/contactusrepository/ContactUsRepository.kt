package com.example.alokozapshopapplication.ui.fragment.contactus.contactusrepository


import com.example.alokozapshopapplication.ui.base.BaseRepository
import com.example.alokozapshopapplication.ui.fragment.contactus.contactus.ContactUsRequest
import com.example.alokozapshopapplication.ui.fragment.contactus.contactus.ContactUsResponse
import com.example.alokozapshopapplication.ui.network.Client.ApiInterface
import com.example.alokozapshopapplication.ui.network.Client.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactUsRepository(val apiInterface: ApiInterface) : BaseRepository()  {

    suspend fun getContactUs(contactUsRequest: ContactUsRequest): ResponseHandler<ContactUsResponse> {
        return withContext(Dispatchers.Default) {
            return@withContext makeAPICall(
                call = {
                    apiInterface.getContactUs(
                        name = contactUsRequest.name.toString(),
                        email = contactUsRequest.email.toString(),
                        telephone = contactUsRequest.telephone.toString(),
                        comment = contactUsRequest.comment.toString()
                      )
                })
        }
    }


}
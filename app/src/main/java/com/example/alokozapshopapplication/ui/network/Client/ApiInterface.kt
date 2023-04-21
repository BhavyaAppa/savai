package com.example.alokozapshopapplication.ui.network.Client

import com.example.alokozapshopapplication.ui.fragment.aboutfragment.newsmedia.model.NewsMediaResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.privacystatement.model.PrivacyStatementResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.refundexchange.model.RefundExchangeResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.shippingdelivery.model.ShippingDeliveryResponce
import com.example.alokozapshopapplication.ui.fragment.aboutfragment.termscondition.model.TermsConditionResponce
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.addtocartitem.AddToCartItemResponse
import com.example.alokozapshopapplication.ui.fragment.cartfragment.model.emptycart.EmptyCartResponce
import com.example.alokozapshopapplication.ui.fragment.categoryfragment.model.CategoryItemResponse
import com.example.alokozapshopapplication.ui.fragment.contactus.contactus.ContactUsResponse
import com.example.alokozapshopapplication.ui.fragment.countryfragment.model.CountryResponse
import com.example.alokozapshopapplication.ui.fragment.currencyfragment.model.CurrencyResponse
import com.example.alokozapshopapplication.ui.fragment.dealsfragment.model.deals.DealsResponse
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.timeslotmodel.TimeSlotResponse
import com.example.alokozapshopapplication.ui.fragment.deliverytimeslot.model.deliveryinfomodel.DeliveryInfoResponce
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.HomeResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.addtocart.AddToCartResponse
import com.example.alokozapshopapplication.ui.fragment.homefragment.homemodelclass.removecart.RemoveCartResponse
import com.example.alokozapshopapplication.ui.fragment.languagefragment.model.LanguageResponce
import com.example.alokozapshopapplication.ui.fragment.loginfragment.loginmodelclass.LoginResponse
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.deleteaccountmodel.DeleteAccountResponce
import com.example.alokozapshopapplication.ui.fragment.myaccountfragment.model.profiledata.ProfileResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.removewishlist.RemoveWishListResponse
import com.example.alokozapshopapplication.ui.fragment.mywishlist.model.getwishlist.WishListResponse
import com.example.alokozapshopapplication.ui.fragment.productdetailsdata.model.ProductDetailsResponse
import com.example.alokozapshopapplication.ui.fragment.referfragment.model.ReferResponse
import com.example.alokozapshopapplication.ui.fragment.reviewpayment.model.ReviewPaymentResponse
import com.example.alokozapshopapplication.ui.fragment.rewardhistoryfragment.model.RewardHistoryResponse
import com.example.alokozapshopapplication.ui.fragment.searchfragment.model.searchmodel.SearchDataRespoance
import com.example.alokozapshopapplication.ui.fragment.signupfragment.signupmodel.SignUpResponce
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.CategoryItemListResponse
import com.example.alokozapshopapplication.ui.fragment.subcategoryfragment.model.wishlist.AddWishListResponse
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by rajanbhavsar on 20/11/19, 3:59 PM
 * Package Name : com.appname.structure.network.Client
 * Project Name : BrainvireStructure
 */
interface ApiInterface {

    @FormUrlEncoded
    @POST("mobikulhttp/customer/logIn")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("storeId") storeId: String = "1",
        @Field("websiteId") websiteId: String = "1",
        @Field("currency") currency: String = "AED",
        @Field("mFactor") mFactor: String = "3.5",
        @Field("width") width: String = "1440",
        @Field("quoteId") quoteId: String = "0",
        @Field("token") token: String = " ",
        @Field("os") os: String = "android"
    ): Response<LoginResponse>


    @FormUrlEncoded
    @POST("mobikulhttp/customer/createAccount")
    suspend fun userSignUp(
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("mobileNumber") mobileNumber: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirmPassword") confirmPassword: String,
        @Field("storeId") storeId: String = "1",
        @Field("websiteId") websiteId: String = "1",
        @Field("mFactor") mFactor: String = "3.5",
        @Field("width") width: String = "1440",
        @Field("quoteId") quoteId: String = "0",
        @Field("token") token: String = "",
        @Field("os") os: String = "android",
        @Field("isSocial") isSocial: String = "0",
        @Field("mobileNumberPrefix") mobileNumberPrefix: String = "1",
        @Field("referralCode") referralCode: String = "",
        @Field("otp") otp: String = "",
    ): Response<SignUpResponce>


    @GET("mobikulhttp/customer/accountinfoData")
    suspend fun getProfileData(
        @Query("eTag") eTag: String = "",
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("customerToken") customerToken: String,
    ): Response<ProfileResponse>

    @FormUrlEncoded
    @POST("mobikulhttp/customer/deleteAccount")
    suspend fun getDeleteAccount(
        @Field("storeId") storeId: String = "3",
        @Field("currency") currency: String = "EUR",
        @Field("quoteId") quoteId: String = "",
        @Field("width") width: String = "828.000000",
        @Field("customerToken") customerToken: String,
        @Field("websiteId") websiteId: String = "2"
    ): Response<DeleteAccountResponce>

    @GET("mobikulhttp/extra/cmsData")
    suspend fun getNewsMedia(@Query("id") id: String = "22"): Response<NewsMediaResponce>

    @GET("mobikulhttp/extra/cmsData")
    suspend fun getPrivacyStatement(@Query("id") id: String = "10"): Response<PrivacyStatementResponce>

    @GET("mobikulhttp/extra/cmsData")
    suspend fun getShippingDelivery(@Query("id") id: String = "13"): Response<ShippingDeliveryResponce>

    @GET("mobikulhttp/extra/cmsData")
    suspend fun getTermsCondition(@Query("id") id: String = "8"): Response<TermsConditionResponce>

    @GET("mobikulhttp/extra/cmsData")
    suspend fun getRefundExchange(@Query("id") id: String = "12"): Response<RefundExchangeResponce>


    @FormUrlEncoded
    @POST("mobikulhttp/checkout/addtoCart")
    suspend fun getAddToCart(
        @Field("websiteId") websiteId: String = "1",
        @Field("customerToken") customerToken: String,
        @Field("currency") currency: String = "AED",
        @Field("storeId") storeId: String = "1",
        @Field("productId") productId: String,
        @Field("quoteId") quoteId: String,
        @Field("qty") qty: String = "1",
        @Field("width") width: String = "828.000000",
    ): Response<AddToCartResponse>


    @FormUrlEncoded
    @POST("mobikulhttp/checkout/removeCartItem")
    suspend fun removeCart(
        @Field("websiteId") websiteId: String = "1",
        @Field("customerToken") customerToken: String,
        @Field("currency") currency: String = "AED",
        @Field("storeId") storeId: String = "1",
        @Field("quoteId") quoteId: String,
        @Field("width") width: String = "828.000000",
        @Field("itemId") itemId: String
    ): Response<RemoveCartResponse>


    @GET("mobikulhttp/catalog/homepagedata")
    suspend fun getHomeData(

        @Query("eTag") eTag: String = "",
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("quoteId") quoteId: String = "",
        @Query("customerToken") customerToken: String,
        @Query("currency") currency: String = "AED",
        @Query("width") width: String = "1440",
        @Query("mFactor") mFactor: String = "3.5",
        @Query("isFromUrl") isFromUrl: String = "0",
        @Query("url") url: String = "",
        @Query("pageNumber") pageNumber: String = "1",
        ): Response<HomeResponse>



    @GET("mobikulhttp/catalog/homepagedata")
    suspend fun getDealsData(
        @Query("eTag") eTag: String = "",
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("quoteId") quoteId: String = "",
        @Query("customerToken") customerToken: String,
        @Query("currency") currency: String = "AED",
        @Query("width") width: String = "1440",
        @Query("mFactor") mFactor: String = "3.5",
        @Query("isFromUrl") isFromUrl: String = "0",
        @Query("url") url: String = "",
        @Query("pageNumber") pageNumber: String = "1",
    ): Response<DealsResponse>


    @GET("mobikulhttp/checkout/cartDetails")
    suspend fun getAddToCartDetails(
        @Query("eTag") eTag: String = "",
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("customerToken") customerToken: String,
        @Query("quoteId") quoteId: String,
        @Query("width") width: String = "1440",
        @Query("currency") currency: String = "AED",
        @Query("method") method: String = "customer",
    ): Response<AddToCartItemResponse>


    @POST("mobikulhttp/checkout/EmptyCart")
    suspend fun getAddToCartEmpty(
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("customerToken") customerToken: String,
        @Query("quoteId") quoteId: String,
        @Query("currency") currency: String = "AED",
        @Query("width") width: String = "828.000000",
    ): Response<EmptyCartResponce>


    @GET("mobikulhttp/catalog/categoriesData")
    suspend fun getCategoryItem(): Response<CategoryItemResponse>

    @GET("mobikulhttp/catalog/productCollection")
    suspend fun getCategoryItemList(
        @Query("storeId") storeId: String = "1",
        @Query("quoteId") quoteId: String = "237080",
        @Query("customerToken") customerToken: String,
        @Query("currency") currency: String = "AED",
        @Query("width") width: String = "1440",
        @Query("mFactor") mFactor: String = "3.5",
        @Query("type") type: String,
        @Query("id") id: String,
        @Query("pageNumber") pageNumber: Int,
    ): Response<CategoryItemListResponse>

    @GET("mobikulhttp/catalog/productPageData")
    suspend fun productPageData(
        @Query("eTag") eTag: String = "",
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("customerToken") customerToken: String,
        @Query("quoteId") quoteId: String,
        @Query("width") width: String = "1440",
        @Query("currency") currency: String = "AED",
        @Query("productId") productId: String,
    ): Response<ProductDetailsResponse>



    @GET("mobikulhttp/customer/referfriend")
    suspend fun referFriend(

        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("customerToken") customerToken : String ,
        @Query("quoteId") quoteId: String,
        @Query("width") width: String = "828.000000",
        @Query("currency") currency: String = "AED",

        ): Response<ReferResponse>


    @GET("mobikulhttp/customer/rewardHistory")
    suspend fun rewardHistory(
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("customerToken") customerToken : String ,
        @Query("pageNumber") pageNumber: String = "1",
    ): Response<RewardHistoryResponse>


    @GET("mobikulhttp/store/countryListing")
    suspend fun getCountryListing(
        @Query("platform") platform: String = "android",
        @Query("version") version: String = "3.8",
    ): Response<CountryResponse>




    @GET("mobikulhttp/store/currencyListing")
    suspend fun getCurrencyListing(
        @Query("platform") platform: String = "android",
        @Query("version") version: String = "3.8",
        @Query("storeId") storeId: String = "1"

    ): Response<CurrencyResponse>



    @GET("mobikulhttp/store/languageListing")
    suspend fun getLanguageListing(
        @Query("platform") platform: String = "android",
        @Query("version") version: String = "3.8",
        @Query("countryId") countryId: String ,
        @Query("websiteId") websiteId: String ,
        @Query("timeStamp") timeStamp: String = ""


    ): Response<LanguageResponce>



    @GET("mobikulhttp/catalog/homepagedata")
    suspend fun searchCategory(
        @Query("eTag") eTag: String = "",
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("quoteId") quoteId: String="" ,
        @Query("customerToken") customerToken: String ,
        @Query("currency") currency: String = "AED",
        @Query("width") width: String = "1440",
        @Query("mFactor") mFactor: String = "3.5",
        @Query("isFromUrl") isFromUrl: String = "0",
        @Query("url") url: String = "",
        @Query("pageNumber") pageNumber: String = "1",
    ): Response<HomeResponse>



    @GET("mobikulhttp/catalog/searchautocomplete")
    suspend fun searchItem(
        @Query("websiteId") websiteId: String = "1",
        @Query("storeId") storeId: String = "1",
        @Query("q") q: String,
        @Query("cat") cat: Boolean = false,
    ): Response<SearchDataRespoance>



    @GET("rest/V1/checkout/timeslots/610")
    suspend fun getTimeSlot(
        @Query("storeId") storeId: String = "1",
    ): Response<TimeSlotResponse>



    @POST("rest/V1/checkout/setslot")
    suspend fun getTimeDateSlot(
        @Query("quoteId") quoteId: String ,
        @Query("slotId") slotId: String ,
    ): Response<DeliveryInfoResponce>



    @FormUrlEncoded
    @POST("mobikulhttp/checkout/reviewandpayment")
    suspend fun getReviewAndPayment(
        @Field("websiteId") websiteId: String = "1",
        @Field("storeId") storeId: String = "1",
        @Field("customerToken") customerToken: String,
        @Field("quoteId") quoteId: String,
        @Field("width") width: String = "1440",
        @Field("currency") currency: String = "AED",
        @Field("method") method: String = "customer",
        @Field("shippingMethod") shippingMethod: String = "",
    ): Response<ReviewPaymentResponse>


    @FormUrlEncoded
    @POST("mobikulhttp/contact/post")
    suspend fun getContactUs(
        @Field("storeId") storeId: String = "1",
        @Field("name") name: String ,
        @Field("email") email: String ,
        @Field("telephone") telephone: String ,
        @Field("comment") comment: String ,
    ): Response<ContactUsResponse>



    @GET("mobikulhttp/customer/wishList")
    suspend fun wishList(
        @Query("eTag") eTag: String = "",
        @Query("storeId") storeId: String = "1",
        @Query("customerToken") customerToken: String ,
        @Query("currency") currency: String = "AED",
        @Query("pageNumber") pageNumber: Int,
    ): Response<WishListResponse>



    @FormUrlEncoded
    @POST("mobikulhttp/catalog/addtoWishlist")
    suspend fun addWishList(
        @Field("storeId") storeId: String = "1",
        @Field("websiteId") websiteId: String="1" ,
        @Field("customerToken") customerToken: String ,
        @Field("productId") productId: String ,
    ): Response<AddWishListResponse>



    @DELETE("mobikulhttp/customer/removefromWishlist")
    suspend fun removeWishList(
        @Query("storeId") storeId: String = "1",
        @Query("websiteId") websiteId: String="1" ,
        @Query("customerToken") customerToken: String ,
        @Query("itemId") itemId: String ,

    ): Response<RemoveWishListResponse>



}
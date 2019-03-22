package com.software.ttsl.RestApi;

import com.software.ttsl.NewModule.AgentInfoResponse;
import com.software.ttsl.NewModule.SailScheduleRequest;
import com.software.ttsl.NewModule.SailScheduleResponse;
import com.software.ttsl.Request.AccountDataModel;
import com.software.ttsl.Request.CallDataModel;
import com.software.ttsl.Request.DealDataModel;
import com.software.ttsl.Request.EventDataModel;
import com.software.ttsl.Request.LeadDataModel;
import com.software.ttsl.Request.SailingScheduleRequest;
import com.software.ttsl.Request.SyncAllDataModel;
import com.software.ttsl.Request.TaskDataModel;
import com.software.ttsl.Response.AccountType;
import com.software.ttsl.Response.DealStage;
import com.software.ttsl.Response.ImageResponse;
import com.software.ttsl.Response.PendingInvoiceResponce;
import com.software.ttsl.Response.PortDataResponse;
import com.software.ttsl.Response.SailingScheduleResponse;
import com.software.ttsl.Response.TrackingNoConstraintResponse;
import com.software.ttsl.Response.TrackingResponse;
import com.software.ttsl.Response.UserAuthenticateResponse;
import com.software.ttsl.Response.VesselListResponse;
import com.software.ttsl.model.AddContactData;
import com.software.ttsl.model.BillListData;
import com.software.ttsl.model.CustomerChallengeModel;
import com.software.ttsl.model.SalesBudgetModel;
import com.software.ttsl.model.SalesUtilityData;
import com.software.ttsl.model.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers("Content-Type: application/json")
    @POST("authentication/{userId}")
    Call<UserAuthenticateResponse> authenticateUser(@Body User user, @Path("userId") String userId);

    @Headers("Content-Type: application/json")
    @GET("tracking")
    Call<List<TrackingNoConstraintResponse>> getTrackingNoConstraint();

    @Headers("Content-Type: application/json")
    @GET("ports")
    Call<Map<String, List<PortDataResponse>>> getPortData();

    @Headers("Content-Type: application/json")
    @GET("pendingInvoices/{userName}")
    Call<List<PendingInvoiceResponce>> getPendingInvoices(@Path("userName") String userId);

    @Headers("Content-Type: application/json")
    @GET("bldata")
    Call<List<BillListData>> getBLLists(@Query("userType") String userType, @Query("userId") String userId);


    @Headers("Content-Type: application/json")
    @POST("sailingSchedule")
    Call<List<SailingScheduleResponse>> getSailingSchedule(@Query("FromETD") String fromETD,
                                                           @Query("ToETD") String toETD,
                                                           @Query("FromETA") String fromETA,
                                                           @Query("ToETA") String toETA, @Body SailingScheduleRequest sailingScheduleRequest);



    @Headers("Content-Type: application/json")
    @POST("sailSchedual")
    Call<List<SailScheduleResponse>> getSailSchedules(@Query("FromETD") String fromETD,
                                                      @Query("ToETD") String toETD,
                                                      @Query("FromETA") String fromETA,
                                                      @Query("ToETA") String toETA, @Body SailScheduleRequest sailScheduleRequest);



    @Headers("Content-Type: application/json")
    @GET("agent/{port}")
    Call<List<AgentInfoResponse>> getAgentInformation(@Path("port") String agentPort);

    @Headers("Content-Type: application/json")
    @GET("vessel")
    Call<List<VesselListResponse>> getVesselList();

    @Headers("Content-Type: application/json")
    @GET("trace/{type}/{TransactionId}")
    Call<TrackingResponse> getTraceingData(@Path("type") int type, @Path("TransactionId") String TransactionId);

    @Headers("Content-Type: application/json")
    @GET("lead/rating")
    Call<List<SalesUtilityData>> getLeadRating();


    @Headers("Content-Type: application/json")
    @PUT("addLead")
    Call<String> addLead(@Body LeadDataModel leadDataModel,  @Header("userid") String id);


    @Headers("Content-Type: application/json")
    @GET("account/AccountType")
    Call<List<AccountType>> getAccountType();

    @Headers("Content-Type: application/json")
    @GET("stage/StageList")
    Call<List<DealStage>> getDealStage();

    @Headers("Content-Type: application/json")
    @PUT("addAccount")
    Call<String> addAccount(@Body AccountDataModel accountDataModel,@Header("userid") String id);

    @Headers("Content-Type: application/json")
    @PUT("addContact")
    Call<String> addContact(@Body AddContactData addContactData);

    @Headers("Content-Type: application/json")
    @PUT("addTask")
    Call<String> addTask(@Body TaskDataModel taskDataModel);


    @Headers("Content-Type: application/json")
    @PUT("addDeals")
    Call<String> addDeal(@Body DealDataModel dealDataModel,@Header("userid") String id);

    @Headers("Content-Type: application/json")
    @PUT("addCall")
    Call<String> addCall(@Body CallDataModel callDataModel);

    @Headers("Content-Type: application/json")
    @PUT("addEvent")
    Call<String> addEvent(@Body EventDataModel eventDataModel,@Header("userid") String id);

    @Headers("Content-Type: application/json")
    @PUT("customerChallenge")
    Call<String> customerChallenge(@Body CustomerChallengeModel customerChallenge,@Header("userid") String id);


    @Headers("Content-Type: application/json")
    @POST("salesBudget")
    Call<String> salesBudget(@Body SalesBudgetModel salesBudgetModel,@Header("userid") String id);

    @Headers("Content-Type: application/json")
    @PUT("addDetail")
    Call<String>syncDetail(@Body SyncAllDataModel syncAllDataModel);

    @Headers("Content-Type: application/json")
    @DELETE("removeLead/{leadId}")
    Call<String> removeLead(@Path("leadId") long leadId);

    @Headers("Content-Type: application/json")
    @DELETE("removeAccount/{id}")
    Call<String> removeAccount(@Path("id") long id);

    @Headers("Content-Type: application/json")
    @DELETE("removeContact/{contactId}")
    Call<String> removeContact(@Path("contactId") long contactId);

    @Headers("Content-Type: application/json")
    @DELETE("removeTask/{taskId}")
    Call<String> removeTask(@Path("taskId") long taskId);

    @Headers("Content-Type: application/json")
    @DELETE("removeCall/{id}")
    Call<String> removeCall(@Path("id") long id);

    @Headers("Content-Type: application/json")
    @DELETE("removeCustChallenge/{id}")
    Call<String> removeCustChallenge(@Path("id") long id,@Header("userid") String userId);

    @Headers("Content-Type: application/json")
    @DELETE("removeEvent/{id}")
    Call<String> removeEvent(@Path("id") long id ,@Header("userid") String userId);

    @Headers("Content-Type: application/json")
    @DELETE("removeSalesBudget/{id}")
    Call<String> removeSalesBudget(@Path("id") long id,@Header("userid") String userId);

    @Headers("Content-Type: application/json")
    @DELETE("removeDeal/{id}")
    Call<String> removeDeal(@Path("id") long id);

    @Headers("Content-Type: application/json")
    @GET("leads/{leadId}")
    Call<List<LeadDataModel>> getAllLeads(@Path("leadId") long leadId);

    @Headers("Content-Type: application/json")
    @GET("imagePath/{imageId}")
    Call<List<ImageResponse>> getImage(@Path("imageId") long imageId);

    @Multipart
    @Headers("Content-Type: multipart/form-data")
    @POST("imagePathStore")
    Call<String> addImage(/*@Body ImageResponse imageResponse*/  @Part MultipartBody.Part file , @Header("imageId") long id,@Header("type") String type);


    @Headers("Content-Type: application/json")
    @GET("accounts/{id}")
    Call<List<AccountDataModel>> getAllAccount(@Path("id") long accountId);

    @Headers("Content-Type: application/json")
    @GET("calls/{id}")
    Call<List<CallDataModel>> getAllCall(@Path("id") long callId);

    @Headers("Content-Type: application/json")
    @GET("contact/{id}")
    Call<List<AddContactData>> getAllContact(@Path("id") long contactId);

    @Headers("Content-Type: application/json")
    @GET("customerChallengeList/{id}")
    Call<List<CustomerChallengeModel>> getAllCustomer(@Path("id") long customerId);


    @Headers("Content-Type: application/json")
    @GET("deals/{id}")
    Call<List<DealDataModel>> getAllDeal(@Path("id") long dealId);

    @Headers("Content-Type: application/json")
    @GET("events/{id}")
    Call<List<EventDataModel>> getAllEvent(@Path("id") long eventId);

    @Headers("Content-Type: application/json")
    @GET("leads/{leadId}")
    Call<List<LeadDataModel>> getAllLead(@Path("leadId") long leadId);


    @Headers("Content-Type: application/json")
    @GET("leads/{leadId}")
    Call<List<LeadDataModel>>getLeadData(@Path("leadId") long leadId);

    @Headers("Content-Type: application/json")
    @GET("salesBudgetList/{id}")
    Call<List<SalesBudgetModel>> getAllSalesBudget(@Path("id") long salesBudgetId);

    @Headers("Content-Type: application/json")
    @GET("tasks/{taskId}")
    Call<List<TaskDataModel>> getAllTask(@Path("taskId") long taskId);
}

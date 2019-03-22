package com.software.ttsl.Utils;

import com.software.ttsl.Response.TrackingNoConstraintResponse;
import com.software.ttsl.model.TrackingConstraint;

import java.util.HashMap;

public class SingletonTrackingConstraint {

   private static SingletonTrackingConstraint singletonTrackingConstraint = null;

   private HashMap<String,TrackingNoConstraintResponse> trackingConstraintHashMap= null;


   private SingletonTrackingConstraint(){

       trackingConstraintHashMap = new HashMap<>();
   }

    public  static SingletonTrackingConstraint getInstance(){

       if(singletonTrackingConstraint==null)
       {
           singletonTrackingConstraint = new SingletonTrackingConstraint();
       }
       return  singletonTrackingConstraint;
   }




   public HashMap<String,TrackingNoConstraintResponse> getHashMap(){
       if(trackingConstraintHashMap ==null)
            trackingConstraintHashMap = new HashMap<>();
       return trackingConstraintHashMap;
   }

}

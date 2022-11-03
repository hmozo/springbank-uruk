package com.doctorkernel.user.query.api.domain.services;

import com.doctorkernel.user.core.domain.entities.User;
import com.doctorkernel.user.core.infra.ddbb.mongo.data.UserData;

public class UserUtilities {
    public static UserData mappingUserDomain2Data(User user){
        var userData= UserData.builder()
                .id(user.getId()).firstName(user.getFirstName()).lastname(user.getLastname())
                .emailAddress(user.getEmailAddress()).account(user.getAccount()).build();
        return userData;
    }

    public static User mappingUserData2Domain(UserData userData){
        var user= User.builder()
                .id(userData.getId()).firstName(userData.getFirstName()).lastname(userData.getLastname())
                .emailAddress(userData.getEmailAddress()).account(userData.getAccount()).build();
        return user;
    }
}

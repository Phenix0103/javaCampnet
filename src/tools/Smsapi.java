/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import com.twilio.Twilio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 * @author MSI
 */
public class Smsapi {

    public static final String ACCOUNT_SID = "AC1e983dc9538950ea1641376d39d630bd";
    public static final String AUTH_TOKEN = "AC1e983dc9538950ea1641376d39d630bd";

    public static void sendSMS(String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+21692165719"),new PhoneNumber("+13182521626"), msg).create();

        System.out.println(message.getSid());

    } 
}


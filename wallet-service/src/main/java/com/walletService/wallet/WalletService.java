package com.walletService.wallet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
	
	@Autowired
	WalletRepo walletRepo;

	private static final String USER_CREATE = "user_create";
	private static final int ONBOARDING_AMT = 50;
	
	
	//Consumer wrt to user service
	//Topic which the consumer must listen to
	@KafkaListener(topics = USER_CREATE,groupId = "e-wallet")
	public void createWallet(String message) throws Exception {
		JSONObject obj = (JSONObject) new JSONParser().parse(message);
		if(!obj.containsKey("userId")) {
			throw new Exception("UserId is not present in the user event");
		}
		
		int userId = (int) obj.get("userId");
		
		
		Wallet wallet = Wallet.builder().balance(ONBOARDING_AMT).userId(userId).build();
		
		walletRepo.save(wallet);
		
		
	}
	
	//Producer and Consumer wrt transaction
	
	
	public void updateWallet() {
		
	}
	
}

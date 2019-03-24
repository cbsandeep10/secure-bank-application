package com.example.banking.bank_app.controller;
import com.example.banking.bank_app.model.Auth_user;
import com.example.banking.bank_app.model.OTPSystem;
import com.example.banking.bank_app.model.User;
import com.example.banking.bank_app.service.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
public class OTPSystemController {

	UserService userService;

	private Map<String, OTPSystem> otp_data = new HashMap<>();
	private final static String ACCOUNT_SID = "ACd6818abaa8a05f3974bb728b97f4962b";
	private final static String AUTH_ID = "1f54ef2518984ea7a7471662b4e53ea5 ";

	static {
		Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	@RequestMapping(value = { "/otp" }, method = RequestMethod.GET)
	public ModelAndView otp() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("otp"); // resources/template/login.html
		return modelAndView;
	}

	@RequestMapping(value = "/mobilenumbers/{mobilenumber}", method = RequestMethod.POST)
	public ModelAndView sendOTP(@PathVariable("mobilenumber") String mobilenumber) {
		OTPSystem otpSystem = new OTPSystem();
		otpSystem.setMobilenumber(mobilenumber);
		otpSystem.setOtp(String.valueOf((int) (Math.random() * (1000)) + 1000));
		otpSystem.setExpirytime(System.currentTimeMillis() + 1000000);
		otp_data.put(mobilenumber, otpSystem);
		Message.creator(new PhoneNumber("+12138338030"), new PhoneNumber("+13852009852"),
				"Your OTP is " + otpSystem.getOtp()).create();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("otp2"); // resources/template/login.html
		return modelAndView;

//		return new ResponseEntity<>("OTP is send successfully", HttpStatus.OK);
	}


//	@RequestMapping (value = "/register", method = RequestMethod.POST)
//	public ModelAndView registerUser(@Valid Auth_user user, BindingResult bindingResult, ModelMap modelMap) {
//		ModelAndView modelAndView = new ModelAndView();
//
//		//check for the validations
//		if (bindingResult.hasErrors()) {
//			modelAndView.addObject("successMessage", "Please Correct the Errors!");
//			modelMap.addAttribute("bindingResult", bindingResult);
//		}
//		else if (userService.userAlreadyExist(user)){
//			modelAndView.addObject("successMessage", "user already exists!");
//		}
//		// if no errors, then save user
//		else {
//			userService.saveUser(user);
//			modelAndView.addObject("successMessage", "User Registered Successfully");
//		}
//
//		modelAndView.addObject("user", new Auth_user());
//		modelAndView.setViewName("register");
//		return modelAndView;
//	}




	@RequestMapping(value = "/mobilenumbers/{mobilenumber}/otp", method = RequestMethod.PUT)
	public ResponseEntity verifyOTP(@PathVariable("mobilenumber") String mobilenumber, @RequestBody OTPSystem requestBodyOTPSystem) {

		if(requestBodyOTPSystem.getOtp()==null || requestBodyOTPSystem.getOtp().trim().length()<=0) {
			return new ResponseEntity<>("Please provide OTP", HttpStatus.BAD_REQUEST);
//			ModelAndView modelAndView = new ModelAndView();
//			modelAndView.setViewName("login");
//			return modelAndView;
		}
		
		if(otp_data.containsKey(mobilenumber)) {
			OTPSystem otpSystem = otp_data.get(mobilenumber);
			if(otpSystem!=null) {
				if(otpSystem.getExpirytime()>= System.currentTimeMillis()) {
					if(otpSystem.getOtp().equals(requestBodyOTPSystem.getOtp())) {
						otp_data.remove(mobilenumber);
						ModelAndView modelAndView = new ModelAndView();
						modelAndView.setViewName("home"); // resources/template/login.html
//						return modelAndView;
						return new ResponseEntity<>("OTP is verified successfully", HttpStatus.OK);

					}
					return new ResponseEntity<>("Invalid OTP", HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<>("OTP is expired...", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Something went wrong...", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Mobile number not found", HttpStatus.NOT_FOUND);
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("login");
//		return modelAndView;
	}
}
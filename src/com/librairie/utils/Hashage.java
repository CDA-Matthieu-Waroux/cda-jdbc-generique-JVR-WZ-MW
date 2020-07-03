package com.librairie.utils;

import java.util.Random;

public class Hashage {

	public static String hash(String pParams) {

		Random random = new Random();

		pParams = BCrypt.hashpw(pParams, BCrypt.gensalt(random.nextInt(16)));

		return pParams;
	}
}

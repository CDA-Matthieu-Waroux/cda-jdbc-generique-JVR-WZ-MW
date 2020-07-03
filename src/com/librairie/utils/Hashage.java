package com.librairie.utils;

public class Hashage {

	public static String hash(String pParams) {

		pParams = BCrypt.hashpw(pParams, BCrypt.gensalt(10));

		return pParams;
	}
}

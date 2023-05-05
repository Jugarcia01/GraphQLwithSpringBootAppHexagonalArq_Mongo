package com.jgo.demo_graphql.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class EmailToUUID {

  private static final String ALGORITHM = "SHA-256";

  public static UUID generateUUID(String email) {
    try {
      // Generate MD5 hash of the email string
      MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
      byte[] bytes = messageDigest.digest(email.getBytes(StandardCharsets.UTF_8));
      // Set the UUID version 5 and namespace to the UUID namespace for DNS
      UUID uuid = UUID.nameUUIDFromBytes(bytes);
      // Return the new UUID
      return new UUID(uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());

    } catch (NoSuchAlgorithmException ex) {
      throw new RuntimeException(ex);
    }
  }

}

package encryption;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author HassanNazar
 */
public class MyRSA {

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger privateKey;
    private BigInteger MypublicKey;
    private BigInteger Mymodulus;
    private BigInteger HispublicKey;
    private BigInteger Hismodulus;

    public BigInteger[] MyRSA(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        Mymodulus = p.multiply(q);
        MypublicKey = new BigInteger("65537");     // common value in practice = 2^16 + 1
        privateKey = MypublicKey.modInverse(phi);
        
        BigInteger[] PublicData = new BigInteger[2];
        PublicData[0] = MypublicKey;
        PublicData[1] = Mymodulus;
        return PublicData;
    }
    
    public void SetHisPK(BigInteger publicKey, BigInteger modulus){
        this.HispublicKey = publicKey;
        this.Hismodulus = modulus;
    }
    
    public BigInteger encrypt(BigInteger message) {
      return message.modPow(HispublicKey, Hismodulus);
   }
    
    public BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(privateKey, Mymodulus);
   }

    public BigInteger getMypublicKey() {
        return MypublicKey;
    }

    public BigInteger getMymodulus() {
        return Mymodulus;
    }

    public BigInteger getHispublicKey() {
        return HispublicKey;
    }

    public BigInteger getHismodulus() {
        return Hismodulus;
    }
}

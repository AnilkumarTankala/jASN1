/**
 * This class file was automatically generated by jASN1 (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.presentation.generated;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.LinkedList;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;

public class User_data {

	public byte[] code = null;
	public BerOctetString simply_encoded_data = null;

	public Fully_encoded_data fully_encoded_data = null;

	public User_data() {
	}

	public User_data(byte[] code) {
		this.code = code;
	}

	public User_data(BerOctetString simply_encoded_data, Fully_encoded_data fully_encoded_data) {
		this.simply_encoded_data = simply_encoded_data;
		this.fully_encoded_data = fully_encoded_data;
	}

	public int encode(BerByteArrayOutputStream berOStream, boolean explicit) throws IOException {
		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				berOStream.write(code[i]);
			}
			return code.length;

		}
		int codeLength = 0;
		if (fully_encoded_data != null) {
			codeLength += fully_encoded_data.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.APPLICATION_CLASS, BerIdentifier.CONSTRUCTED, 1)).encode(berOStream);
			return codeLength;

		}
		
		if (simply_encoded_data != null) {
			codeLength += simply_encoded_data.encode(berOStream, false);
			codeLength += (new BerIdentifier(BerIdentifier.APPLICATION_CLASS, BerIdentifier.PRIMITIVE, 0)).encode(berOStream);
			return codeLength;

		}
		
		throw new IOException("Error encoding BerChoice: No item in choice was selected.");
	}

	public int decode(InputStream iStream, BerIdentifier berIdentifier) throws IOException {
		int codeLength = 0;
		if (berIdentifier == null) {
			berIdentifier = new BerIdentifier();
			codeLength += berIdentifier.decode(iStream);
		}
		if (berIdentifier.equals(BerIdentifier.APPLICATION_CLASS, BerIdentifier.PRIMITIVE, 0)) {
			simply_encoded_data = new BerOctetString();
			codeLength += simply_encoded_data.decode(iStream, false);
			return codeLength;
		}
		if (berIdentifier.equals(BerIdentifier.APPLICATION_CLASS, BerIdentifier.CONSTRUCTED, 1)) {
			fully_encoded_data = new Fully_encoded_data();
			codeLength += fully_encoded_data.decode(iStream, false);
			return codeLength;
		}
		throw new IOException("Error decoding BerChoice: Identifier matched to no item.");
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream berOStream = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(berOStream, false);
		code = berOStream.getArray();
	}
}


/*
 * Copyright 2011-14 Fraunhofer ISE
 *
 * This file is part of jasn1.
 * For more information visit http://www.openmuc.org
 *
 * jasn1 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * jasn1 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with jasn1.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.openmuc.jasn1.ber.types;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openmuc.jasn1.ber.BerByteArrayOutputStream;
import org.openmuc.jasn1.ber.BerIdentifier;
import org.openmuc.jasn1.ber.BerLength;

public class BerIntegerTest {

	public class IntegerUnivPrim extends BerInteger {

		// in the final version identifier needs to be static
		protected final BerIdentifier identifier = new BerIdentifier(BerIdentifier.APPLICATION_CLASS,
				BerIdentifier.PRIMITIVE, 2);

		IntegerUnivPrim(long val) {
			super(val);
		}

		@Override
		public int encode(BerByteArrayOutputStream berBAOStream, boolean explicit) throws IOException {
			int codeLength = super.encode(berBAOStream, false);
			if (explicit) {
				codeLength += BerLength.encodeLength(berBAOStream, codeLength);
				codeLength += identifier.encode(berBAOStream);
			}

			return codeLength;
		}

	}

	@Test
	public void encodeDecodeLargeLongs() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);
		BerInteger myInt = new BerInteger(20093243433l);
		myInt.encode(berBAOStream, true);

		ByteArrayInputStream berInputStream = new ByteArrayInputStream(berBAOStream.getArray());
		BerInteger myInt2 = new BerInteger();
		myInt2.decode(berInputStream, true);
		Assert.assertEquals(20093243433l, myInt2.val);
	}

	@Test
	public void encodeDecodeLargeNegativeLongs() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);
		BerInteger myInt = new BerInteger(-20093243433l);
		myInt.encode(berBAOStream, true);

		ByteArrayInputStream berInputStream = new ByteArrayInputStream(berBAOStream.getArray());
		BerInteger myInt2 = new BerInteger();
		myInt2.decode(berInputStream, true);
		Assert.assertEquals(-20093243433l, myInt2.val);
	}

	@Test
	public void implicitEncoding1() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);

		// 51 is the example in X.690
		IntegerUnivPrim testInteger = new IntegerUnivPrim(51);
		int length = testInteger.encode(berBAOStream, false);
		Assert.assertEquals(2, length);

		byte[] expectedBytes = new byte[] { 0x01, 0x33 };
		Assert.assertArrayEquals(expectedBytes, berBAOStream.getArray());

	}

	@Test
	public void implicitEncoding2() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);

		IntegerUnivPrim testInteger = new IntegerUnivPrim(256);
		int length = testInteger.encode(berBAOStream, false);
		Assert.assertEquals(3, length);

		byte[] expectedBytes = new byte[] { 0x02, 0x01, 0x00 };
		Assert.assertArrayEquals(expectedBytes, berBAOStream.getArray());

	}

	@Test
	public void implicitEncoding3() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);

		IntegerUnivPrim testInteger = new IntegerUnivPrim(0);
		int length = testInteger.encode(berBAOStream, false);
		Assert.assertEquals(2, length);

		byte[] expectedBytes = new byte[] { 0x01, 0x00 };
		Assert.assertArrayEquals(expectedBytes, berBAOStream.getArray());

	}

	@Test
	public void implicitEncoding4() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);

		IntegerUnivPrim testInteger = new IntegerUnivPrim(127);
		int length = testInteger.encode(berBAOStream, false);
		Assert.assertEquals(2, length);

		byte[] expectedBytes = new byte[] { 0x01, 0x7f };
		Assert.assertArrayEquals(expectedBytes, berBAOStream.getArray());

	}

	@Test
	public void implicitEncoding5() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);

		IntegerUnivPrim testInteger = new IntegerUnivPrim(128);
		int length = testInteger.encode(berBAOStream, false);
		Assert.assertEquals(3, length);

		byte[] expectedBytes = new byte[] { 0x02, 0x00, (byte) 0x80 };
		Assert.assertArrayEquals(expectedBytes, berBAOStream.getArray());

	}

	@Test
	public void implicitEncoding6() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);

		IntegerUnivPrim testInteger = new IntegerUnivPrim(-128);
		int length = testInteger.encode(berBAOStream, false);
		Assert.assertEquals(2, length);

		byte[] expectedBytes = new byte[] { 0x01, (byte) 0x80 };
		Assert.assertArrayEquals(expectedBytes, berBAOStream.getArray());

	}

	@Test
	public void implicitEncoding7() throws IOException {
		BerByteArrayOutputStream berBAOStream = new BerByteArrayOutputStream(50);

		IntegerUnivPrim testInteger = new IntegerUnivPrim(-129);
		int length = testInteger.encode(berBAOStream, false);
		Assert.assertEquals(3, length);

		byte[] expectedBytes = new byte[] { 0x02, (byte) 0xff, (byte) 0x7f };
		Assert.assertArrayEquals(expectedBytes, berBAOStream.getArray());

	}

	@Test
	public void explicitEncoding() throws IOException {
		BerByteArrayOutputStream berStream = new BerByteArrayOutputStream(50);

		// 51 is the example in X.690
		BerInteger testInteger = new BerInteger(51);
		int length = testInteger.encode(berStream, true);
		Assert.assertEquals(3, length);

		byte[] expectedBytes = new byte[] { 0x02, 0x01, 0x33 };
		Assert.assertArrayEquals(expectedBytes, berStream.getArray());
	}

	@Test
	public void explicitDecoding() throws IOException {
		byte[] byteCode = new byte[] { 0x02, 0x01, 0x33 };
		ByteArrayInputStream berInputStream = new ByteArrayInputStream(byteCode);
		BerInteger asn1Integer = new BerInteger();
		asn1Integer.decode(berInputStream, true);
		Assert.assertEquals(51, asn1Integer.val);
	}

	@Test
	public void explicitEncoding2() throws IOException {
		BerByteArrayOutputStream berStream = new BerByteArrayOutputStream(50);

		BerInteger testInteger = new BerInteger(5555);
		int length = testInteger.encode(berStream, true);
		Assert.assertEquals(4, length);

		// System.out.println(getByteArrayString(berStream.getArray()));

		byte[] expectedBytes = new byte[] { 0x02, 0x02, 0x15, (byte) 0xb3 };
		Assert.assertArrayEquals(expectedBytes, berStream.getArray());
	}

	@Test
	public void explicitDecoding2() throws IOException {
		byte[] byteCode = new byte[] { 0x02, 0x02, 0x15, (byte) 0xb3 };
		ByteArrayInputStream berInputStream = new ByteArrayInputStream(byteCode);
		BerInteger asn1Integer = new BerInteger();
		asn1Integer.decode(berInputStream, true);
		Assert.assertEquals(5555, asn1Integer.val);
	}

	@Test
	public void explicitDecoding3() throws IOException {
		byte[] byteCode = new byte[] { 0x02, 0x01, (byte) 0xc0 };
		ByteArrayInputStream berInputStream = new ByteArrayInputStream(byteCode);
		BerInteger asn1Integer = new BerInteger();
		asn1Integer.decode(berInputStream, true);
		Assert.assertEquals(-64, asn1Integer.val);
	}

	@Test
	public void explicitDecoding4() throws IOException {
		byte[] byteCode = new byte[] { 0x02, 0x02, (byte) 0xff, 0x01 };
		ByteArrayInputStream berInputStream = new ByteArrayInputStream(byteCode);
		BerInteger asn1Integer = new BerInteger();
		asn1Integer.decode(berInputStream, true);
		Assert.assertEquals(-255, asn1Integer.val);
	}

	public static String getByteArrayString(byte[] byteArray) {
		StringBuilder builder = new StringBuilder();
		int l = 1;
		for (byte b : byteArray) {
			if ((l != 1) && ((l - 1) % 8 == 0)) {
				builder.append(' ');
			}
			if ((l != 1) && ((l - 1) % 16 == 0)) {
				builder.append('\n');
			}
			l++;
			builder.append("0x");
			String hexString = Integer.toHexString(b & 0xff);
			if (hexString.length() == 1) {
				builder.append(0);
			}
			builder.append(hexString + " ");
		}
		return builder.toString();
	}

}

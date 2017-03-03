/**
 * This class file was automatically generated by jASN1 v1.6.0 (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.presentation.generated.iso8823_presentation;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;


public class CPType {

	public static class NormalModeParameters {

		public static final BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 16);
		protected BerIdentifier id;

		public byte[] code = null;
		public ProtocolVersion protocolVersion = null;

		public CallingPresentationSelector callingPresentationSelector = null;

		public CalledPresentationSelector calledPresentationSelector = null;

		public PresentationContextDefinitionList presentationContextDefinitionList = null;

		public DefaultContextName defaultContextName = null;

		public PresentationRequirements presentationRequirements = null;

		public UserSessionRequirements userSessionRequirements = null;

		public UserData userData = null;

		public NormalModeParameters() {
			id = identifier;
		}

		public NormalModeParameters(byte[] code) {
			id = identifier;
			this.code = code;
		}

		public NormalModeParameters(ProtocolVersion protocolVersion, CallingPresentationSelector callingPresentationSelector, CalledPresentationSelector calledPresentationSelector, PresentationContextDefinitionList presentationContextDefinitionList, DefaultContextName defaultContextName, PresentationRequirements presentationRequirements, UserSessionRequirements userSessionRequirements, UserData userData) {
			id = identifier;
			this.protocolVersion = protocolVersion;
			this.callingPresentationSelector = callingPresentationSelector;
			this.calledPresentationSelector = calledPresentationSelector;
			this.presentationContextDefinitionList = presentationContextDefinitionList;
			this.defaultContextName = defaultContextName;
			this.presentationRequirements = presentationRequirements;
			this.userSessionRequirements = userSessionRequirements;
			this.userData = userData;
		}

		public int encode(BerByteArrayOutputStream os, boolean explicit) throws IOException {

			int codeLength;

			if (code != null) {
				codeLength = code.length;
				for (int i = code.length - 1; i >= 0; i--) {
					os.write(code[i]);
				}
			}
			else {
				codeLength = 0;
				if (userData != null) {
					codeLength += userData.encode(os, true);
				}
				
				if (userSessionRequirements != null) {
					codeLength += userSessionRequirements.encode(os, false);
					// write tag: CONTEXT_CLASS, PRIMITIVE, 9
					os.write(0x89);
					codeLength += 1;
				}
				
				if (presentationRequirements != null) {
					codeLength += presentationRequirements.encode(os, false);
					// write tag: CONTEXT_CLASS, PRIMITIVE, 8
					os.write(0x88);
					codeLength += 1;
				}
				
				if (defaultContextName != null) {
					codeLength += defaultContextName.encode(os, false);
					// write tag: CONTEXT_CLASS, CONSTRUCTED, 6
					os.write(0xa6);
					codeLength += 1;
				}
				
				if (presentationContextDefinitionList != null) {
					codeLength += presentationContextDefinitionList.encode(os, false);
					// write tag: CONTEXT_CLASS, CONSTRUCTED, 4
					os.write(0xa4);
					codeLength += 1;
				}
				
				if (calledPresentationSelector != null) {
					codeLength += calledPresentationSelector.encode(os, false);
					// write tag: CONTEXT_CLASS, PRIMITIVE, 2
					os.write(0x82);
					codeLength += 1;
				}
				
				if (callingPresentationSelector != null) {
					codeLength += callingPresentationSelector.encode(os, false);
					// write tag: CONTEXT_CLASS, PRIMITIVE, 1
					os.write(0x81);
					codeLength += 1;
				}
				
				if (protocolVersion != null) {
					codeLength += protocolVersion.encode(os, false);
					// write tag: CONTEXT_CLASS, PRIMITIVE, 0
					os.write(0x80);
					codeLength += 1;
				}
				
				codeLength += BerLength.encodeLength(os, codeLength);
			}

			if (explicit) {
				codeLength += id.encode(os);
			}

			return codeLength;

		}

		public int decode(InputStream is, boolean explicit) throws IOException {
			int codeLength = 0;
			int subCodeLength = 0;
			BerIdentifier berIdentifier = new BerIdentifier();

			if (explicit) {
				codeLength += id.decodeAndCheck(is);
			}

			BerLength length = new BerLength();
			codeLength += length.decode(is);

			int totalLength = length.val;
			codeLength += totalLength;

			if (totalLength == -1) {
				subCodeLength += berIdentifier.decode(is);

				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)) {
					protocolVersion = new ProtocolVersion();
					subCodeLength += protocolVersion.decode(is, false);
					subCodeLength += berIdentifier.decode(is);
				}
				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
					callingPresentationSelector = new CallingPresentationSelector();
					subCodeLength += callingPresentationSelector.decode(is, false);
					subCodeLength += berIdentifier.decode(is);
				}
				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)) {
					calledPresentationSelector = new CalledPresentationSelector();
					subCodeLength += calledPresentationSelector.decode(is, false);
					subCodeLength += berIdentifier.decode(is);
				}
				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 4)) {
					presentationContextDefinitionList = new PresentationContextDefinitionList();
					subCodeLength += presentationContextDefinitionList.decode(is, false);
					subCodeLength += berIdentifier.decode(is);
				}
				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 6)) {
					defaultContextName = new DefaultContextName();
					subCodeLength += defaultContextName.decode(is, false);
					subCodeLength += berIdentifier.decode(is);
				}
				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 8)) {
					presentationRequirements = new PresentationRequirements();
					subCodeLength += presentationRequirements.decode(is, false);
					subCodeLength += berIdentifier.decode(is);
				}
				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 9)) {
					userSessionRequirements = new UserSessionRequirements();
					subCodeLength += userSessionRequirements.decode(is, false);
					subCodeLength += berIdentifier.decode(is);
				}
				if (berIdentifier.tagNumber == 0 && berIdentifier.identifierClass == 0 && berIdentifier.primitive == 0) {
					int nextByte = is.read();
					if (nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}
				userData = new UserData();
				int choiceDecodeLength = userData.decode(is, berIdentifier);
				if (choiceDecodeLength != 0) {
					subCodeLength += choiceDecodeLength;
					subCodeLength += berIdentifier.decode(is);
				}
				else {
					userData = null;
				}

				int nextByte = is.read();
				if (berIdentifier.tagNumber != 0 || berIdentifier.identifierClass != 0 || berIdentifier.primitive != 0
				|| nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}

			if (totalLength == 0) {
				return codeLength;
			}
			subCodeLength += berIdentifier.decode(is);
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 0)) {
				protocolVersion = new ProtocolVersion();
				subCodeLength += protocolVersion.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berIdentifier.decode(is);
			}
			
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 1)) {
				callingPresentationSelector = new CallingPresentationSelector();
				subCodeLength += callingPresentationSelector.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berIdentifier.decode(is);
			}
			
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 2)) {
				calledPresentationSelector = new CalledPresentationSelector();
				subCodeLength += calledPresentationSelector.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berIdentifier.decode(is);
			}
			
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 4)) {
				presentationContextDefinitionList = new PresentationContextDefinitionList();
				subCodeLength += presentationContextDefinitionList.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berIdentifier.decode(is);
			}
			
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 6)) {
				defaultContextName = new DefaultContextName();
				subCodeLength += defaultContextName.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berIdentifier.decode(is);
			}
			
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 8)) {
				presentationRequirements = new PresentationRequirements();
				subCodeLength += presentationRequirements.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berIdentifier.decode(is);
			}
			
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.PRIMITIVE, 9)) {
				userSessionRequirements = new UserSessionRequirements();
				subCodeLength += userSessionRequirements.decode(is, false);
				if (subCodeLength == totalLength) {
					return codeLength;
				}
				subCodeLength += berIdentifier.decode(is);
			}
			
			userData = new UserData();
			int choiceDecodeLength = userData.decode(is, berIdentifier);
			subCodeLength += choiceDecodeLength;
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

			
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
			encode(os, false);
			code = os.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder("SEQUENCE{");
			boolean firstSelectedElement = true;
			if (protocolVersion != null) {
				sb.append("protocolVersion: ").append(protocolVersion);
				firstSelectedElement = false;
			}
			
			if (callingPresentationSelector != null) {
				if (!firstSelectedElement) {
					sb.append(", ");
				}
				sb.append("callingPresentationSelector: ").append(callingPresentationSelector);
				firstSelectedElement = false;
			}
			
			if (calledPresentationSelector != null) {
				if (!firstSelectedElement) {
					sb.append(", ");
				}
				sb.append("calledPresentationSelector: ").append(calledPresentationSelector);
				firstSelectedElement = false;
			}
			
			if (presentationContextDefinitionList != null) {
				if (!firstSelectedElement) {
					sb.append(", ");
				}
				sb.append("presentationContextDefinitionList: ").append(presentationContextDefinitionList);
				firstSelectedElement = false;
			}
			
			if (defaultContextName != null) {
				if (!firstSelectedElement) {
					sb.append(", ");
				}
				sb.append("defaultContextName: ").append(defaultContextName);
				firstSelectedElement = false;
			}
			
			if (presentationRequirements != null) {
				if (!firstSelectedElement) {
					sb.append(", ");
				}
				sb.append("presentationRequirements: ").append(presentationRequirements);
				firstSelectedElement = false;
			}
			
			if (userSessionRequirements != null) {
				if (!firstSelectedElement) {
					sb.append(", ");
				}
				sb.append("userSessionRequirements: ").append(userSessionRequirements);
				firstSelectedElement = false;
			}
			
			if (userData != null) {
				if (!firstSelectedElement) {
					sb.append(", ");
				}
				sb.append("userData: ").append(userData);
				firstSelectedElement = false;
			}
			
			sb.append("}");
			return sb.toString();
		}

	}

	public static final BerIdentifier identifier = new BerIdentifier(BerIdentifier.UNIVERSAL_CLASS, BerIdentifier.CONSTRUCTED, 17);
	protected BerIdentifier id;

	public byte[] code = null;
	public ModeSelector modeSelector = null;

	public NormalModeParameters normalModeParameters = null;

	public CPType() {
		id = identifier;
	}

	public CPType(byte[] code) {
		id = identifier;
		this.code = code;
	}

	public CPType(ModeSelector modeSelector, NormalModeParameters normalModeParameters) {
		id = identifier;
		this.modeSelector = modeSelector;
		this.normalModeParameters = normalModeParameters;
	}

	public int encode(BerByteArrayOutputStream os, boolean explicit) throws IOException {

		int codeLength;

		if (code != null) {
			codeLength = code.length;
			for (int i = code.length - 1; i >= 0; i--) {
				os.write(code[i]);
			}
		}
		else {
			codeLength = 0;
			if (normalModeParameters != null) {
				codeLength += normalModeParameters.encode(os, false);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
				os.write(0xa2);
				codeLength += 1;
			}
			
			codeLength += modeSelector.encode(os, false);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
			os.write(0xa0);
			codeLength += 1;
			
			codeLength += BerLength.encodeLength(os, codeLength);
		}

		if (explicit) {
			codeLength += id.encode(os);
		}

		return codeLength;

	}

	public int decode(InputStream is, boolean explicit) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerIdentifier berIdentifier = new BerIdentifier();

		if (explicit) {
			codeLength += id.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		while (subCodeLength < totalLength) {
			subCodeLength += berIdentifier.decode(is);
			if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 0)) {
				modeSelector = new ModeSelector();
				subCodeLength += modeSelector.decode(is, false);
			}
			else if (berIdentifier.equals(BerIdentifier.CONTEXT_CLASS, BerIdentifier.CONSTRUCTED, 2)) {
				normalModeParameters = new NormalModeParameters();
				subCodeLength += normalModeParameters.decode(is, false);
			}
		}
		if (subCodeLength != totalLength) {
			throw new IOException("Length of set does not match length tag, length tag: " + totalLength + ", actual set length: " + subCodeLength);

		}
		codeLength += subCodeLength;

		return codeLength;
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
		encode(os, false);
		code = os.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("SEQUENCE{");
		sb.append("modeSelector: ").append(modeSelector);
		
		if (normalModeParameters != null) {
			sb.append(", ");
			sb.append("normalModeParameters: ").append(normalModeParameters);
		}
		
		sb.append("}");
		return sb.toString();
	}

}

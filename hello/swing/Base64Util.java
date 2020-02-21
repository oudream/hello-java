package app;

public final class Base64Util
{
    public static byte[] removeWhiteSpace(byte[] data)
    {
        if (data == null) {
            return null;
        }
        int newSize = 0;
        int len = data.length;
        for (int i = 0; i < len; i++) {
            if (!isWhiteSpace(data[i])) {
                newSize++;
            }
        }
        if (newSize == len) {
            return data;
        }
        byte[] newArray = new byte[newSize];
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (!isWhiteSpace(data[i])) {
                newArray[(j++)] = data[i];
            }
        }
        return newArray;
    }

    public static boolean isWhiteSpace(byte octect)
    {
        return (octect == 32) || (octect == 13) || (octect == 10) || (octect == 9);
    }

    public static boolean isData(byte octect)
    {
        return base64Alphabet[octect] != -1;
    }

    public static boolean isPad(byte octect)
    {
        return octect == 61;
    }

    public static byte[] encode(byte[] binaryData)
    {
        if (binaryData == null) {
            return null;
        }
        int lengthDataBits = binaryData.length * 8;
        int fewerThan24bits = lengthDataBits % 24;
        int numberTriplets = lengthDataBits / 24;
        byte[] encodedData = (byte[])null;
        if (fewerThan24bits != 0) {
            encodedData = new byte[(numberTriplets + 1) * 4];
        } else {
            encodedData = new byte[numberTriplets * 4];
        }
        byte k = 0;
        byte l = 0;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        int i = 0;
        for (i = 0; i < numberTriplets; i++)
        {
            dataIndex = i * 3;
            b1 = binaryData[dataIndex];
            b2 = binaryData[(dataIndex + 1)];
            b3 = binaryData[(dataIndex + 2)];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            encodedIndex = i * 4;
            byte val1 = (b1 & 0xFFFFFF80) != 0 ? (byte)(b1 >> 2 ^ 0xC0) : (byte)(b1 >> 2);
            byte val2 = (b2 & 0xFFFFFF80) != 0 ? (byte)(b2 >> 4 ^ 0xF0) : (byte)(b2 >> 4);
            byte val3 = (b3 & 0xFFFFFF80) != 0 ? (byte)(b3 >> 6 ^ 0xFC) : (byte)(b3 >> 6);
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
            encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2 | val3)];
            encodedData[(encodedIndex + 3)] = lookUpBase64Alphabet[(b3 & 0x3F)];
        }
        dataIndex = i * 3;
        encodedIndex = i * 4;
        if (fewerThan24bits == 8)
        {
            b1 = binaryData[dataIndex];
            k = (byte)(b1 & 0x3);
            byte val1 = (b1 & 0xFFFFFF80) != 0 ? (byte)(b1 >> 2 ^ 0xC0) : (byte)(b1 >> 2);
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(k << 4)];
            encodedData[(encodedIndex + 2)] = 61;
            encodedData[(encodedIndex + 3)] = 61;
        }
        else if (fewerThan24bits == 16)
        {
            b1 = binaryData[dataIndex];
            b2 = binaryData[(dataIndex + 1)];
            l = (byte)(b2 & 0xF);
            k = (byte)(b1 & 0x3);
            byte val1 = (b1 & 0xFFFFFF80) != 0 ? (byte)(b1 >> 2 ^ 0xC0) : (byte)(b1 >> 2);
            byte val2 = (b2 & 0xFFFFFF80) != 0 ? (byte)(b2 >> 4 ^ 0xF0) : (byte)(b2 >> 4);
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[(encodedIndex + 1)] = lookUpBase64Alphabet[(val2 | k << 4)];
            encodedData[(encodedIndex + 2)] = lookUpBase64Alphabet[(l << 2)];
            encodedData[(encodedIndex + 3)] = 61;
        }
        return encodedData;
    }

    public static byte[] decode(byte[] base64Data)
    {
        if (base64Data == null) {
            return null;
        }
        base64Data = removeWhiteSpace(base64Data);
        if (base64Data.length % 4 != 0) {
            return null;
        }
        int numberQuadruple = base64Data.length / 4;
        if (numberQuadruple == 0) {
            return new byte[0];
        }
        byte[] decodedData = (byte[])null;
        byte b1 = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte d1 = 0;
        byte d2 = 0;
        byte d3 = 0;
        byte d4 = 0;
        int i = 0;
        int encodedIndex = 0;
        int dataIndex = 0;
        decodedData = new byte[numberQuadruple * 3];
        for (; i < numberQuadruple - 1; i++)
        {
            if ((!isData(d1 = base64Data[(dataIndex++)])) || (!isData(d2 = base64Data[(dataIndex++)])) || (!isData(d3 = base64Data[(dataIndex++)])) || (!isData(d4 = base64Data[(dataIndex++)]))) {
                return null;
            }
            b1 = base64Alphabet[d1];
            b2 = base64Alphabet[d2];
            b3 = base64Alphabet[d3];
            b4 = base64Alphabet[d4];
            decodedData[(encodedIndex++)] = ((byte)(b1 << 2 | b2 >> 4));
            decodedData[(encodedIndex++)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
            decodedData[(encodedIndex++)] = ((byte)(b3 << 6 | b4));
        }
        if ((!isData(d1 = base64Data[(dataIndex++)])) || (!isData(d2 = base64Data[(dataIndex++)]))) {
            return null;
        }
        b1 = base64Alphabet[d1];
        b2 = base64Alphabet[d2];
        d3 = base64Data[(dataIndex++)];
        d4 = base64Data[(dataIndex++)];
        if ((!isData(d3)) || (!isData(d4)))
        {
            if ((isPad(d3)) && (isPad(d4)))
            {
                if ((b2 & 0xF) != 0) {
                    return null;
                }
                byte[] tmp = new byte[i * 3 + 1];
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                tmp[encodedIndex] = ((byte)(b1 << 2 | b2 >> 4));
                return tmp;
            }
            if ((!isPad(d3)) && (isPad(d4)))
            {
                b3 = base64Alphabet[d3];
                if ((b3 & 0x3) != 0) {
                    return null;
                }
                byte[] tmp = new byte[i * 3 + 2];
                System.arraycopy(decodedData, 0, tmp, 0, i * 3);
                tmp[(encodedIndex++)] = ((byte)(b1 << 2 | b2 >> 4));
                tmp[encodedIndex] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
                return tmp;
            }
            return null;
        }
        b3 = base64Alphabet[d3];
        b4 = base64Alphabet[d4];
        decodedData[(encodedIndex++)] = ((byte)(b1 << 2 | b2 >> 4));
        decodedData[(encodedIndex++)] = ((byte)((b2 & 0xF) << 4 | b3 >> 2 & 0xF));
        decodedData[(encodedIndex++)] = ((byte)(b3 << 6 | b4));
        return decodedData;
    }

    private static final byte[] base64Alphabet = new byte[256];
    private static final byte[] lookUpBase64Alphabet = new byte[64];

    static
    {
        int i = 0;
        for (; i < 255; i++) {
            base64Alphabet[i] = -1;
        }
        for (i = 90; i >= 65; i--) {
            base64Alphabet[i] = ((byte)(i - 65));
        }
        for (i = 122; i >= 97; i--) {
            base64Alphabet[i] = ((byte)(i - 97 + 26));
        }
        for (i = 57; i >= 48; i--) {
            base64Alphabet[i] = ((byte)(i - 48 + 52));
        }
        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;
        for (i = 0; i <= 25; i++) {
            lookUpBase64Alphabet[i] = ((byte)(65 + i));
        }
        i = 26;
        for (int j = 0; i <= 51; j++)
        {
            lookUpBase64Alphabet[i] = ((byte)(97 + j));
            i++;
        }
        i = 52;
        for (int j = 0; i <= 61; j++)
        {
            lookUpBase64Alphabet[i] = ((byte)(48 + j));
            i++;
        }
        lookUpBase64Alphabet[62] = 43;
        lookUpBase64Alphabet[63] = 47;
    }
}

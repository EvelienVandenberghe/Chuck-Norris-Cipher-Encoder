# Chuck-Norris-Cipher-Encoder

This is a Java console application from Hyperskill that encodes and decodes text using the Chuck Norris Unary Code (a binary encoding system that uses only zeros and spaces).

# How It Works #
### Encoding Process  

Convert to Binary: Each character is converted to its 7-bit binary representation  
Group Consecutive Bits: Group all consecutive 1s and 0s together  
Encode Groups: Each group becomes two blocks:  

First block: 0 (for series of 1s) or 00 (for series of 0s)  
Second block: Number of zeros = count of bits in the series  
  
### Decoding Process  

Split into Blocks: Split the encoded string by spaces  
Process Pairs: Read blocks in pairs (type + count)  
Rebuild Binary: Convert each pair back to binary bits  
Convert to Text: Split binary into 7-bit chunks and convert to characters  

### Features  

- Encode text to Chuck Norris Unary Code  
- Decode Chuck Norris Unary Code back to text  
- Interactive menu interface  
- Input validation with error messages  
- Supports letters, numbers, spaces, and punctuation  
- Handles ASCII characters (7-bit encoding)  

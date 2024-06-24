  * Copyright (c) 2018-2019, Texas Instruments Incorporated
     * All rights reserved.
      *
      * Redistribution and use in source and binary forms, with or without
      * modification, are permitted provided that the following conditions
      * are met:
      *
      * *  Redistributions of source code must retain the above copyright
     *    notice, this list of conditions and the following disclaimer.
     *
    * *  Redistributions in binary form must reproduce the above copyright
     *    notice, this list of conditions and the following disclaimer in the
    *    documentation and/or other materials provided with the distribution.
     *
     * *  Neither the name of Texas Instruments Incorporated nor the names of
     *    its contributors may be used to endorse or promote products derived
     *    from this software without specific prior written permission.
     *
     * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
     * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
     * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
     * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
     * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
     * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
     * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
     * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
     * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
     * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
     * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
     */
    /*!****************************************************************************
     *  @file       TRNG.h
     *
     *  @brief      TRNG driver header
     *
     *  @warning    This is a beta API. It may change in future releases.
     *
     *  @anchor ti_drivers_TRNG_Overview
    *  # Overview #
     *  The True Random Number Generator (TRNG) module generates numbers of variable
     *  lengths from a source of entropy. The output is suitable for applications
     *  requiring cryptographically random numbers such as keying material for
     *  private or symmetric keys.
     *
     *  @anchor ti_drivers_TRNG_Usage
     *  # Usage #
     *
     *  ## Before starting a TRNG operation #
     *
     *  Before starting a TRNG operation, the application must do the following:
     *      - Call TRNG_init() to initialize the driver.
     *      - Call TRNG_Params_init() to initialize the TRNG_Params to default values.
     *      - Modify the TRNG_Params as desired.
     *      - Call TRNG_open() to open an instance of the driver.
    *      - Initialize a blank CryptoKey. These opaque datastructures are representations
     *        of keying material and its storage. Depending on how the keying material
     *        is stored (RAM or flash, key store, key blob), the CryptoKey must be
     *        initialized differently. The TRNG API can handle all types of CryptoKey.
     *        However, not all device-specific implementions support all types of CryptoKey.
     *        Devices without a key store will not support CryptoKeys with keying material
     *        stored in a key store for example.
     *        All devices support plaintext CryptoKeys.
    *
    *  ## TRNG operations #
    *
     *  TRNG_generateEntropy() provides the most basic functionality. Use it to
    *  generate random numbers of a specified width without further restrictions.
     *  An example use-case would be generating a symmetric key for AES encryption
     *  and / or authentication.
     *
    *  To generate an ECC private key, you should use rejection sampling to ensure
     *  that the keying material is in the interval [1, n - 1]. The ECDH public key
     *  genreation APIs will reject private keys that are outside of this interval.
     *  This information may be used to generate keying material until a suitable
     *  key is generated. For most curves, it is improbable to generate a random number
   *  outside of this interval because n is a large number close to the maximum
    *  number that would fit in the k-byte keying material array. An example
    *  of how to do this is given below.
     *
    *  ## After the TRNG operation completes #
     *
     *  After the TRNG operation completes, the application should either start another operation
     *  or close the driver by calling TRNG_close().
     *
     *  @anchor ti_drivers_TRNG_Synopsis
     *  ## Synopsis
   *  @anchor ti_drivers_TRNG_Synopsis_Code
    *  @code
    *  // Import TRNG Driver definitions
     *  #include <ti/drivers/TRNG.h>
    *  #include <ti/drivers/cryptoutils/cryptokey/CryptoKeyPlaintext.h>
     *
    *  // Define name for TRNG channel index
     *  #define TRNG_INSTANCE 0
    *
    *  #define KEY_LENGTH_BYTES 16
    *
    *  TRNG_init();
    *
   *  handle = TRNG_open(TRNG_INSTANCE, NULL);
    *
   *  CryptoKeyPlaintext_initBlankKey(&entropyKey, entropyBuffer, KEY_LENGTH_BYTES);
   *
   *  result = TRNG_generateEntropy(handle, &entropyKey);
    *
   *  TRNG_close(handle);
  *
   *  @endcode
  *
   *  @anchor ti_drivers_TRNG_Examples
   *  ## Examples
    *
    *  ### Generate symmetric encryption key #
    *  @code
    *
   *  #include <ti/drivers/TRNG.h>
    *  #include <ti/drivers/cryptoutils/cryptokey/CryptoKeyPlaintext.h>
    *
 *  #define KEY_LENGTH_BYTES 16
   *
    *  TRNG_Handle handle;
    *  int_fast16_t result;
    *
    *  CryptoKey entropyKey;
  *  uint8_t entropyBuffer[KEY_LENGTH_BYTES];
  *
   *  handle = TRNG_open(0, NULL);
   *
    *  if (!handle) {
    *      // Handle error
    *      while(1);
    *  }
   *
    *  CryptoKeyPlaintext_initBlankKey(&entropyKey, entropyBuffer, KEY_LENGTH_BYTES);
    *
    *  result = TRNG_generateEntropy(handle, &entropyKey);
    *
    *  if (result != TRNG_STATUS_SUCCESS) {
    *      // Handle error
    *      while(1);
    *  }
    *
    *  TRNG_close(handle);
    *
    *  @endcode
    *
   *  ### Generate ECC private and public key using rejection sampling #
    *  @code
    *
    *  #include <ti/drivers/TRNG.h>
   *  #include <ti/drivers/ECDH.h>
    *  #include <ti/drivers/cryptoutils/cryptokey/CryptoKeyPlaintext.h>
    *  #include <ti/drivers/cryptoutils/ecc/ECCParams.h>
    *
    *  TRNG_Handle trngHandle;
    *  ECDH_Handle ecdhHandle;
    *
    *  CryptoKey privateKey;
    *  CryptoKey publicKey;
    *
   *  int_fast16_t trngResult;
   *  int_fast16_t ecdhResult;
    *
    *  uint8_t privateKeyingMaterial[32];
   *  uint8_t publicKeyingMaterial[64];
   *
    *  ECDH_OperationGeneratePublicKey genPubKeyOperation;
    *
   *  trngHandle = TRNG_open(0, NULL);
    *  if (!trngHandle) {
    *      while(1);
    *  }
    *
    *  ecdhHandle = ECDH_open(0, NULL);
    *  if (!ecdhHandle) {
    *      while(1);
    *  }
    *
    *  // Repeatedly generate random numbers until they are in the range [1, n - 1].
  *  // Since the NIST-P256 order is so close to 2^256, the probability of needing
   *  // to generate more than one random number is incredibly low but not non-zero.
    *  do {
    *
   *      CryptoKeyPlaintext_initBlankKey(&privateKey, privateKeyingMaterial, ECCParams_NISTP256.length);
    *      CryptoKeyPlaintext_initBlankKey(&publicKey, publicKeyingMaterial, 2 * ECCParams_NISTP256.length);
    *
    *      trngResult = TRNG_generateEntropy(trngHandle, &privateKey);
    *
    *      if (trngResult != TRNG_STATUS_SUCCESS) {
    *          while(1);
    *      }
   *
   *      ECDH_OperationGeneratePublicKey_init(&genPubKeyOperation);
  *      genPubKeyOperation.curve = &ECCParams_NISTP256;
    *      genPubKeyOperation.myPrivateKey = &privateKey;
    *      genPubKeyOperation.myPublicKey = &publicKey;
    *
    *      ecdhResult = ECDH_generatePublicKey(ecdhHandle, &genPubKeyOperation);
    *
    *  } while(ecdhResult == ECDH_STATUS_PRIVATE_KEY_LARGER_EQUAL_ORDER || ecdhResult == ECDH_STATUS_PRIVATE_KEY_ZERO);
    *
    *  TRNG_close(trngHandle);
    *  ECDH_close(ecdhHandle);
    *
    *  @endcode
    */
   
   #ifndef ti_drivers_TRNG__include
   #define ti_drivers_TRNG__include
   
   #ifdef __cplusplus
  extern "C" {
  #endif
  
   #include <stdbool.h>
   #include <stddef.h>
   #include <stdint.h>
  
   #include <ti/drivers/cryptoutils/cryptokey/CryptoKey.h>
   
   #define TRNG_STATUS_RESERVED        (-32)
   
   #define TRNG_STATUS_SUCCESS         (0)
   
   #define TRNG_STATUS_ERROR           (-1)
 
   #define TRNG_STATUS_RESOURCE_UNAVAILABLE (-2)
  
   typedef struct TRNG_Config  *TRNG_Handle;
  
  typedef enum {
       TRNG_RETURN_BEHAVIOR_CALLBACK = 1,    
       TRNG_RETURN_BEHAVIOR_BLOCKING = 2,    
      TRNG_RETURN_BEHAVIOR_POLLING  = 4,    
   } TRNG_ReturnBehavior;
   
   typedef struct TRNG_Config {
       void               *object;
   
       void         const *hwAttrs;
   } TRNG_Config;
   
  typedef void (*TRNG_CallbackFxn) (TRNG_Handle handle,
                                    int_fast16_t returnValue,
                                    CryptoKey *entropy);
  
   typedef struct {
       TRNG_ReturnBehavior     returnBehavior;             
       TRNG_CallbackFxn        callbackFxn;                
       uint32_t                timeout;                    
       void                   *custom;                     
   } TRNG_Params;
   
   extern const TRNG_Params TRNG_defaultParams;
   
   void TRNG_init(void);
   
   void TRNG_Params_init(TRNG_Params *params);
   
   TRNG_Handle TRNG_open(uint_least8_t index, TRNG_Params *params);
   
   void TRNG_close(TRNG_Handle handle);
   
   int_fast16_t TRNG_generateEntropy(TRNG_Handle handle, CryptoKey *entropy);
  
   #ifdef __cplusplus
   }
   #endif
   
   #endif /* ti_drivers_TRNG__include */
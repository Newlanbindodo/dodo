const bip39 =require('bip39')
const HDWallet = require('ethereum-hdwallet')
const mnemonic_chinese=bip39.generateMnemonic(128,null,bip39.wordlists.chinese_simplified)
console.log('zhujici:'+mnemonic_chinese)
async function getAddress(mnemonic){
    const seed=await bip39.mnemonicToSeed(mnemonic)
    const hdwallet=HDWallet.fromSeed(seed)
    const key=hdwallet.derive("m/44'/60'/0'/0/0")
    console.log("PrivateKey="+key.getPrivateKey().toString('hex'))
    console.log("PublicKey="+key.getPublicKey().toString('hex'))
    const EthAddress ='0x'+key.getAddress().toString('hex')
    console.log("ETH Address="+EthAddress)


}
getAddress(mnemonic_chinese)

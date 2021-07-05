import org.bitcoinj.core.Address;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.script.Script;

/**
 * @Auther:刘兰斌
 * @Date: 2021/06/07/17:28
 * @Explain:
 */
public class Bitcoinj {
    public static void main(String[] args) {
        genernateadress();
    }
    public static  void genernateadress(){
        RegTestParams regTestParams = RegTestParams.get();
        ECKey key = new ECKey();
//        Address addr = key.toASN1(regTestParams);
//        Address addr = new Address(regTestParams, key.getPubKeyHash()) {
//            @Override
//            public byte[] getHash() {
//                return new byte[0];
//            }
//
//            @Override
//            public Script.ScriptType getOutputScriptType() {
//                return null;
//            }
//        };
//        System.out.format("p2pkh address => %s\n", addr);
        }
    }


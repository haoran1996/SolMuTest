package blockchain.Mutator;

import java.io.File;

public abstract class Mutator {
    abstract String getName();
    abstract String getDescribe();

    abstract boolean isapplicable(String content);

//    abstract String apply(File file);

}

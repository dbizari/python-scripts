// The Unix find command allows you to search for files under a given directory. You can specify criteria for files you are interested in.

// Imagine that you need to write code in a high level language like java, that does things similar to the find command. I would like you to focus on 2 uses cases at first.

// Find all files over 5 MB somewhere under a directory.
// Find all XML files somewhere under a directory.
// I would like you to create a library that lets me do this easily. Keep in mind that these are just 2 uses cases and that the library should be flexible.

// ==> We need to find based on size rule
// ==> We need to find based on file termination

public interface Rule  {
    public boolean applyRule(File file);
}

public class FindFile {
    public List<File> find(Directory baseDirectory, Rule rule) {
        List<File> files = baseDirectory.getFiles();
        List<File> result = new ArrayList<File>();

        for (File f: files) {
            if (f.applyRule()) {
                result.add(f)
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FileRule rule = new FileRuleOr(
                            new FileRuleExtension("java"),
                            new FileRuleAnd(
                                new FileRuleExtension("zip"),
                                new FileRuleSize(5711472, FileRuleSize.FileRuleSizeOp.EQUAL)
                            )
                        );
        find("/home/local/ANT/lestrozi", rule);
    }
}


public class SizeRule implements Rule {
    public enum Operation {
    GREATER,
    // GREATER_OR_EQUAL,
    // LOWER,
    // LOWER_OR_EQUAL;
    }

    private double targetSize;
    private Operation operation;

    // Constructor with all args

    @verride
    public boolean applyRule(File file) {
        return switch (this.operation) {
            case Operation.GREATER -> {
              yield file.getSize() > this.targetSize;
            }
        }
    }
}

public class FileExtensionRule implements Rule {
    private String targetExtensionName;

    // Constructor with all args


    @Override
    public boolean applyRule(File f) {
        return f.getName().endsWith("." + ext);
    }
}

public class FileRuleOr implements Rule {
    private List<Rule> rules;

    // Constructor with all args


    @Override
    public boolean applyRule(File f) {
        for (Rule r: this.rules) {
            if (r.applyRule()) {
                return true;
            }
        }
        return false;
    }
}

public class FileRuleAnd implements Rule {
    private List<Rule> rules;

    // Constructor with all args


    @Override
    public boolean applyRule(File f) {
        for (Rule r: this.rules) {
            if (!r.applyRule()) {
                return false;
            }
        }
        return true;
    }
}

package database;

import java.util.ArrayList;
import java.util.List;

public class OmniaSearchFilters {

    private static final List<String> allowedBaseOperation = new ArrayList<String>(){
        {
            add("AND");
            add("OR");
        }
    };

    private SubSearchFilters[] filters;

    private String baseOperator;

    public OmniaSearchFilters(SubSearchFilters[] filters){
        this.filters = filters;
    }

    public String getBaseOperator() {
        if(baseOperator == null) return "AND";
        return baseOperator;
    }

    public void setBaseOperator(String baseOperator) {
        if(!allowedBaseOperation.contains(baseOperator))
            throw new IllegalArgumentException(String.format("Invalid base" +
                    "operation '%s'", baseOperator));
        this.baseOperator = baseOperator;
    }

    public SubSearchFilters[] getFilters() {
        return filters;
    }

    public static class SubSearchFilters{

        private static final List<String> allowedOperations = new ArrayList<String>(){
            {
                add(":");
                add(">");
                add(">=");
                add(("<="));
                add("<");
                add("<>");
            }
        };

        private String operation;
        private String key;
        private String value;

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {

            if (allowedOperations.contains(operation)) {
                this.operation = operation;
            } else {
                throw new RuntimeException("Invalid operator used for search criteria");
            }
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}

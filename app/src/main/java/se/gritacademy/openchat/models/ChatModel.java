package se.gritacademy.openchat.models;

public class ChatModel {

        private String message;
        private String name;
        private Long timestamp;

        public ChatModel() {
        }

        public ChatModel(String message, String name, Long timestamp) {
            this.message = message;
            this.name = name;
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public String getName() {
            return name;
        }

        public Long getTimestamp() {
            return timestamp;
        }


}


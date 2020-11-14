package com.abrajner.plagiarismdetector.common;

public final class Defaults {
    
    public static final class CommonEntityColumns {
        public static final String ID = "id";
        public static final String FILE_ID = "file_id";
        public static final String REPORT_ID = "report_id";
        public static final String USER_ID = "user_id";
        public static final String GROUP_ID = "group_id";
    }
    
    public static final class UserEntityColumns{
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String IS_ACTIVE = "active";
    }
    
    public static final class ReportEntityColumns{
        public static final String IS_PLAGIARISM = "is_plagiarism";
        public static final String SIMILARITY_PERCENTAGE = "similarity_percentage";
    }
    
    public static final class FileReportEntityColumns{
    
    }
    
    public static final class FileEntityColumns{
        public static final String ATTACHMENT_NAME = "attachment_name";
        public static final String IS_ACTIVE = "active";
    }
    
    public static final class FileGroupEntityColumns{
        public static final String IS_FILE_ACTIVE_IN_GROUP = "file_active_in_group";
    }
    
    public static final class GroupEntityColumns{
        public static final String GROUP_NAME = "group_name";
    }
}

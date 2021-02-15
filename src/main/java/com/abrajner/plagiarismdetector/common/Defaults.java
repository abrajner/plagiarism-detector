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
        public static final String SIMILARITY_PERCENTAGE_WITH_SUBSTITUTION = "similarity_percentage_with_substitution";
        public static final String IS_FINISHED = "is_finished";
        public static final String REPORT_NAME = "report_name";
        public static final String IS_SUBSTITUTION_INCLUDED = "is_substitution_included";
    }
    
    public static final class FileReportEntityColumns{
    
    }
    
    public static final class FileEntityColumns{
        public static final String FILE_NAME = "file_name";
        public static final String IS_ACTIVE = "is_active";
        public static final String IDENTIFIERS = "identifiers";
        public static final String PARSED_FILE_CONTENT = "parsed_file_content";
        public static final String FILE_AUTHOR = "file_author";
    }
    
    public static final class FileGroupEntityColumns{
        public static final String IS_FILE_ACTIVE_IN_GROUP = "file_active_in_group";
    }
    
    public static final class GroupEntityColumns{
        public static final String GROUP_NAME = "group_name";
        public static final String PROGRAMMING_LANGUAGE = "programming_language";
        public static final String FILES = "files";
    }
}

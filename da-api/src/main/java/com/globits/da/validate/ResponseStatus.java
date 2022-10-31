package com.globits.da.validate;
    public enum ResponseStatus {
        // Check Null
        EMPLOYEE_CODE_IS_NULL(6000, "Code can't not null !"),
        EMPLOYEE_NAME_IS_NULL(6001,"Name can't not null"),
        EMPLOYEE_EMAIL_NOT_NULL(6002,"Email can't not null !"),
        PHONE_NUMBER_IS_NULL(6003,"Phone number not null !"),
        PROVINCE_IS_NULL(6004,"Province not found !"),
        DISTRICT_IS_NULL(6005,"District not found !"),
        COMMUNE_IS_NULL(6006, "Commune not found !"),
        PROVINCE_NAME_IS_NULL(6007,"Name province can't null !"),
        PROVINCE_GEOCODE_IS_NULL(6008,"Geocode province is null"),

        DISTRICT_NAME_IS_NULL(6009,"Name District is null"),
        DISTRICT_GEOCODE_IS_NUL(6010,"Geocode district is null"),
        DISTRICT_FOUNDED_YEAR_IS_NULL(6011,"Year founded of District is null"),
        COMMUNE_NAME_IS_NULL(6012,"Commune name is null"),

        CERTIFICATE_IS_NULL(6015,"Certificate is null"),
        CERTIFICATE_NAME_IS_NULL(6013,"Certificate name is null"),
        CERTIFICATE_EFFECTIVE_IS_NULL(6014,"Effective Date of certificate is null"),

        EMPLOYEE_IS_NULL(6016,"Employee is null"),

        CERTIFICATE_EMPLOYEE_ID_PROVINCE_IS_NULL(6017,"Certificate of Employee, ID of Province null"),
        CERTIFICATE_EMPLOYEE_ID_EMPLOYEE_IS_NULL(6018,"ID of Employee is null"),
        CERTIFICATE_EMPLOYEE_ID_CERTIFICATE_IS_NULL(6019,"Certificate ID is null"),



        // Check Exist
        EMPLOYEE_CODE_IS_EXISTS(6001, "Code has exist !"),
        EMPLOYEE_CODE_NO_EXIST(6002,"Employee no Exist !"),


        // Check regex
        EMPLOYEE_EMAIL_REGEX(6005,"Email is malformed !"),
        EMPLOYEE_PHONE_REGEX(6007, "Phone has 11 numbers!"),
        EMPLOYEE_CHECK_AGE(6008, "Age can't negative"),
        EMPLOYEE_CODE_REGEX(6003, "Code is Malformed"),
        DONE(6002,"OK"),

        // Check depend
        DISTRICT_NO_DEPEND_PROVINCE(7002,"District no dependent province ! "),
        COMMUNE_NO_DEPEND_DISTRICT(7003,"Commune no dependent district"),


        //Check Duplicate
        CERTIFICATE_DUPLICATE(8000, "Certificate not duplicate !"),
        CERTIFICATE_NOT_EXPIRATION(8001,"Certificate Expiration !"),

        // check remove

        ;
        ResponseStatus(int code, String message) {
        }
    }


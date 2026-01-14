package com.gymmanagement.gym_management_api.Common;

public final class RoleClaims {
    public static final String ReceptionistClaim = "hasRole('ADMIN')";
    public static final String TrainerClaim = "hasRole('TRAINER')";
    public static final String ClientClaim = "hasRole('CLIENT')";
    public static final String AllClaim = "hasAnyRole('ADMIN','TRAINER','CLIENT')";
    public static final String ReceptionistClientClaim = "hasAnyRole('ADMIN','CLIENT')";
    public static final String ReceptionistTrainerClaim = "hasAnyRole('ADMIN','TRAINER')";
}

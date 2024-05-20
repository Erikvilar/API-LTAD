package comdbbyerik.project.entity.User;

public record AuthenticatedDTORegister (String login, String password, UserRole role){}

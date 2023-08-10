package wanted.preassignment.service;

import wanted.preassignment.dto.request.JoinUserRequest;

public interface UserService {
    public Long join(JoinUserRequest joinMemberRequest);
}

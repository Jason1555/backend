package com.dubinchin.util;

import org.springframework.stereotype.Component;

import com.dubinchin.entity.*;
import com.dubinchin.entity.enums.UserRole;
import com.dubinchin.exception.UnauthorizedAccessException;

@Component
public class AccessControlUtil {

    /**
     * Проверяет, что пользователь является организатором фестиваля
     */
    public void checkFestivalOwnership(Festival festival, User user) {
        if (!festival.getOrganizer().getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("У вас нет прав для работы с этим фестивалем");
        }
    }

    /**
     * Проверяет, что пользователь является организатором
     */
    public void checkOrganizerRole(User user) {
        if (!(user.getRole().equals(UserRole.ORGANIZER))) {
            throw new UnauthorizedAccessException("Только организаторы могут выполнять эту операцию");
        }
    }

    /**
     * Проверяет, что пользователь является клубом
     */
    public void checkClubRole(User user) {
        if (!user.getRole().equals(UserRole.CLUB)) {
            throw new UnauthorizedAccessException("Только клубы могут выполнять эту операцию");
        }
    }

    /**
     * Проверяет, что пользователь владеет клубом
     */
    public void checkClubOwnership(String clubOwnerId, String userId) {
        if (!clubOwnerId.equals(userId)) {
            throw new UnauthorizedAccessException("У вас нет прав для работы с этим клубом");
        }
    }
}
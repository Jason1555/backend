// DataLoader.java
package com.dubinchin.config;

import com.dubinchin.entity.*;
import com.dubinchin.entity.enums.*;
import com.dubinchin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Transactional
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrganizerRepository organizerRepository;
    private final ClubRepository clubRepository;
    private final FestivalRepository festivalRepository;
    private final ApplicationRepository applicationRepository;
    private final PhotographerRepository photographerRepository;
    private final FestivalPhotographerRepository festivalPhotographerRepository;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }

        // ===== USERS =====
        User organizerUser = User.builder()
            .email("organizer@example.com")
            .name("Иван Организатор")
            .role(UserRole.ORGANIZER)
            .build();

        User clubUser1 = User.builder()
            .email("vikings@example.com")
            .name("Клуб «Викинги»")
            .role(UserRole.CLUB)
            .build();

        User clubUser2 = User.builder()
            .email("medieval@example.com")
            .name("Клуб «Средневековье»")
            .role(UserRole.CLUB)
            .build();

        userRepository.saveAll(List.of(organizerUser, clubUser1, clubUser2));

        // ===== ORGANIZER =====
        Organizer organizer = Organizer.builder()
            .user(organizerUser)
            .build();
        organizerRepository.save(organizer);

        // ===== CLUBS =====
        Club vikings = Club.builder()
            .name("Клуб исторической реконструкции «Викинги»")
            .logo("https://via.placeholder.com/150?text=Vikings")
            .description("Реконструкция культуры и быта викингов IX–XI веков")
            .phone("+7 (999) 123-45-67")
            .email("vikings@example.com")
            .website("https://vikings-club.ru")
            .vkLink("https://vk.com/vikings-club")
            .owner(clubUser1)
            .build();

        Club medieval = Club.builder()
            .name("Клуб «Средневековье»")
            .logo("https://via.placeholder.com/150?text=Medieval")
            .description("Реконструкция средневекового быта и военного дела")
            .phone("+7 (999) 234-56-78")
            .email("medieval@example.com")
            .website("https://medieval-club.ru")
            .vkLink("https://vk.com/medieval-club")
            .owner(clubUser2)
            .build();

        clubRepository.saveAll(List.of(vikings, medieval));

        // ===== FESTIVALS =====
        Festival heroes = Festival.builder()
            .name("Эпоха Героев 2026")
            .epoch("IX–XI века")
            .date(LocalDate.of(2026, 6, 15))
            .city("Санкт-Петербург")
            .location("Петропавловская крепость")
            .requirementsFileUrl("/requirements/viking-fest.pdf")
            .createdAt(LocalDate.now())
            .status(FestivalStatus.ONGOING)
            .organizer(organizer)
            .build();

        Festival castle = Festival.builder()
            .name("Рыцарский Замок")
            .epoch("XIV век")
            .date(LocalDate.of(2026, 8, 20))
            .city("Выборг")
            .location("Выборгский замок")
            .requirementsFileUrl("/requirements/medieval.pdf")
            .createdAt(LocalDate.now())
            .status(FestivalStatus.PLANNED)
            .organizer(organizer)
            .build();

        Festival battle = Festival.builder()
            .name("Ледовое Побоище")
            .epoch("XIII век")
            .date(LocalDate.of(2025, 4, 10))
            .city("Псков")
            .location("Изборская крепость")
            .requirementsFileUrl("/requirements/ice-battle.pdf")
            .createdAt(LocalDate.now())
            .status(FestivalStatus.COMPLETED)
            .organizer(organizer)
            .build();

        festivalRepository.saveAll(List.of(heroes, castle, battle));

        // ===== APPLICATIONS =====
        Application app1 = Application.builder()
            .festival(heroes)
            .club(vikings)
            .status(ApplicationStatus.APPROVED)
            .description("Готовы участвовать с полной реконструкцией лагеря.")
            .submittedAt(LocalDateTime.now().minusDays(30))
            .reviewedAt(LocalDateTime.now().minusDays(25))
            .reviewerNotes("Отличная подготовка.")
            .build();

        Application app2 = Application.builder()
            .festival(heroes)
            .club(medieval)
            .status(ApplicationStatus.PENDING)
            .description("Планируем провести показательные бои.")
            .submittedAt(LocalDateTime.now().minusDays(10))
            .build();

        Application app3 = Application.builder()
            .festival(castle)
            .club(medieval)
            .status(ApplicationStatus.REJECTED)
            .description("Заявка на участие в турнире.")
            .submittedAt(LocalDateTime.now().minusDays(20))
            .reviewedAt(LocalDateTime.now().minusDays(18))
            .reviewerNotes("Не соответствует требованиям по экипировке.")
            .build();

        applicationRepository.saveAll(List.of(app1, app2, app3));

        // ===== PHOTOGRAPHERS =====
        Photographer photographer1 = Photographer.builder()
            .name("Алексей Иванов")
            .contactInfo("@alex_photo")
            .portfolioUrl("https://portfolio.example/alex")
            .build();

        Photographer photographer2 = Photographer.builder()
            .name("Мария Смирнова")
            .contactInfo("@maria_photo")
            .portfolioUrl("https://portfolio.example/maria")
            .build();

        Photographer photographer3 = Photographer.builder()
            .name("Дмитрий Петров")
            .contactInfo("@dmitry_photo")
            .portfolioUrl("https://portfolio.example/dmitry")
            .build();

        photographerRepository.saveAll(List.of(photographer1, photographer2, photographer3));

        // ===== FESTIVAL_PHOTOGRAPHERS =====
        FestivalPhotographer fp1 = FestivalPhotographer.builder()
            .festival(heroes)
            .photographer(photographer1)
            .status(PhotographerStatus.HIRED)
            .build();

        FestivalPhotographer fp2 = FestivalPhotographer.builder()
            .festival(castle)
            .photographer(photographer2)
            .status(PhotographerStatus.NEGOTIATING)
            .build();

        festivalPhotographerRepository.saveAll(List.of(fp1, fp2));
    }
}

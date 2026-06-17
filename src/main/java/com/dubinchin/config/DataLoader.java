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
        User organizerUser = new User();
        organizerUser.setId("user-organizer-1");
        organizerUser.setEmail("organizer@example.com");
        organizerUser.setName("Иван Организатор");
        organizerUser.setRole(UserRole.ORGANIZER);

        User clubUser1 = new User();
        clubUser1.setId("user-club-1");
        clubUser1.setEmail("vikings@example.com");
        clubUser1.setName("Клуб «Викинги»");
        clubUser1.setRole(UserRole.CLUB);

        User clubUser2 = new User();
        clubUser2.setId("user-club-2");
        clubUser2.setEmail("medieval@example.com");
        clubUser2.setName("Клуб «Средневековье»");
        clubUser2.setRole(UserRole.CLUB);

        userRepository.saveAll(List.of(organizerUser, clubUser1, clubUser2));

        // ===== ORGANIZER =====
        Organizer organizer = new Organizer();
        organizer.setId("organizer-1");
        organizer.setUser(organizerUser);
        organizerRepository.save(organizer);

        // ===== CLUBS =====
        Club vikings = new Club();
        vikings.setId("club-1");
        vikings.setName("Клуб исторической реконструкции «Викинги»");
        vikings.setLogo("https://via.placeholder.com/150?text=Vikings");
        vikings.setDescription("Реконструкция культуры и быта викингов IX–XI веков");
        vikings.setPhone("+7 (999) 123-45-67");
        vikings.setEmail("vikings@example.com");
        vikings.setWebsite("https://vikings-club.ru");
        vikings.setVkLink("https://vk.com/vikings-club");
        vikings.setOwner(clubUser1);

        Club medieval = new Club();
        medieval.setId("club-2");
        medieval.setName("Клуб «Средневековье»");
        medieval.setLogo("https://via.placeholder.com/150?text=Medieval");
        medieval.setDescription("Реконструкция средневекового быта и военного дела");
        medieval.setPhone("+7 (999) 234-56-78");
        medieval.setEmail("medieval@example.com");
        medieval.setWebsite("https://medieval-club.ru");
        medieval.setVkLink("https://vk.com/medieval-club");
        medieval.setOwner(clubUser2);

        clubRepository.saveAll(List.of(vikings, medieval));

        // ===== FESTIVALS =====
        Festival heroes = new Festival();
        heroes.setId("festival-1");
        heroes.setName("Эпоха Героев 2026");
        heroes.setEpoch("IX–XI века");
        heroes.setDate(LocalDate.of(2026, 6, 15));
        heroes.setCity("Санкт-Петербург");
        heroes.setLocation("Петропавловская крепость");
        heroes.setRequirementsFileUrl("/requirements/viking-fest.pdf");
        heroes.setCreatedAt(LocalDate.now());
        heroes.setStatus(FestivalStatus.ONGOING);
        heroes.setOrganizer(organizer);

        Festival castle = new Festival();
        castle.setId("festival-2");
        castle.setName("Рыцарский Замок");
        castle.setEpoch("XIV век");
        castle.setDate(LocalDate.of(2026, 8, 20));
        castle.setCity("Выборг");
        castle.setLocation("Выборгский замок");
        castle.setRequirementsFileUrl("/requirements/medieval.pdf");
        castle.setCreatedAt(LocalDate.now());
        castle.setStatus(FestivalStatus.PLANNED);
        castle.setOrganizer(organizer);

        Festival battle = new Festival();
        battle.setId("festival-3");
        battle.setName("Ледовое Побоище");
        battle.setEpoch("XIII век");
        battle.setDate(LocalDate.of(2025, 4, 10));
        battle.setCity("Псков");
        battle.setLocation("Изборская крепость");
        battle.setRequirementsFileUrl("/requirements/ice-battle.pdf");
        battle.setCreatedAt(LocalDate.now());
        battle.setStatus(FestivalStatus.COMPLETED);
        battle.setOrganizer(organizer);

        festivalRepository.saveAll(List.of(heroes, castle, battle));

        // ===== APPLICATIONS =====
        Application app1 = new Application();
        app1.setId("application-1");
        app1.setFestival(heroes);
        app1.setClub(vikings);
        app1.setStatus(ApplicationStatus.APPROVED);
        app1.setDescription("Готовы участвовать с полной реконструкцией лагеря.");
        app1.setSubmittedAt(LocalDateTime.now().minusDays(30));
        app1.setReviewedAt(LocalDateTime.now().minusDays(25));
        app1.setReviewerNotes("Отличная подготовка.");

        Application app2 = new Application();
        app2.setId("application-2");
        app2.setFestival(heroes);
        app2.setClub(medieval);
        app2.setStatus(ApplicationStatus.PENDING);
        app2.setDescription("Планируем провести показательные бои.");
        app2.setSubmittedAt(LocalDateTime.now().minusDays(10));

        Application app3 = new Application();
        app3.setId("application-3");
        app3.setFestival(castle);
        app3.setClub(medieval);
        app3.setStatus(ApplicationStatus.REJECTED);
        app3.setDescription("Заявка на участие в турнире.");
        app3.setSubmittedAt(LocalDateTime.now().minusDays(20));
        app3.setReviewedAt(LocalDateTime.now().minusDays(18));
        app3.setReviewerNotes("Не соответствует требованиям по экипировке.");

        applicationRepository.saveAll(List.of(app1, app2, app3));

        // ===== PHOTOGRAPHERS =====
        Photographer photographer1 = new Photographer();
        photographer1.setId("photographer-1");
        photographer1.setName("Алексей Иванов");
        photographer1.setContactInfo("@alex_photo");
        photographer1.setPortfolioUrl("https://portfolio.example/alex");

        Photographer photographer2 = new Photographer();
        photographer2.setId("photographer-2");
        photographer2.setName("Мария Смирнова");
        photographer2.setContactInfo("@maria_photo");
        photographer2.setPortfolioUrl("https://portfolio.example/maria");

        Photographer photographer3 = new Photographer();
        photographer3.setId("photographer-3");
        photographer3.setName("Дмитрий Петров");
        photographer3.setContactInfo("@dmitry_photo");
        photographer3.setPortfolioUrl("https://portfolio.example/dmitry");

        photographerRepository.saveAll(List.of(photographer1, photographer2, photographer3));

        // ===== FESTIVAL_PHOTOGRAPHERS =====
        FestivalPhotographer fp1 = new FestivalPhotographer();
        fp1.setId("festival-photographer-1");
        fp1.setFestival(heroes);
        fp1.setPhotographer(photographer1);
        fp1.setStatus(PhotographerStatus.HIRED);

        FestivalPhotographer fp2 = new FestivalPhotographer();
        fp2.setId("festival-photographer-2");
        fp2.setFestival(castle);
        fp2.setPhotographer(photographer2);
        fp2.setStatus(PhotographerStatus.NEGOTIATING);

        festivalPhotographerRepository.saveAll(List.of(fp1, fp2));
    }
}

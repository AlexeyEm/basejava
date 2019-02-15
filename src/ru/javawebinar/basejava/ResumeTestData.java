package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = createResume("uuid1", "Григорий Кислин");
        System.out.println(resume);
    }

    public static Resume createResume(String uuid, String fullName){
        Resume resume = new Resume(uuid, fullName);

        Map<ContactType, String> contactMap = new EnumMap<>(ContactType.class);
        contactMap.put(ContactType.PHONE, "+7(921) 855-0482");
        contactMap.put(ContactType.SKYPE, "grigory.kislin");
        contactMap.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contactMap.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contactMap.put(ContactType.GITHUB, "https://github.com/gkislin");
        contactMap.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contactMap.put(ContactType.SITE, "http://gkislin.ru/");
        resume.setContacts(contactMap);

        Map<SectionType, AbstractSection> sectionMap = new EnumMap<>(SectionType.class);
        sectionMap.put(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sectionMap.put(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> achievement = new ArrayList<>();
        achievement.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievement.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievement.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievement.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievement.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achievement.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        sectionMap.put(SectionType.ACHIEVEMENT, new ListSection(achievement));

        List<String> qualification = new ArrayList<>();
        qualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        qualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        sectionMap.put(SectionType.QUALIFICATIONS, new ListSection(qualification));

        List<Experience> experience = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок"));
        experience.add(new Experience("Java Online Projects", "http://javaops.ru/", new ArrayList<>(positions)));
        positions.clear();
        positions.add(new Position(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experience.add(new Experience("Wrike", "https://www.wrike.com/", new ArrayList<>(positions)));
        positions.clear();
        positions.add(new Position(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"));
        experience.add(new Experience("RIT Center", null, new ArrayList<>(positions)));
        sectionMap.put(SectionType.EXPERIENCE, new ExperienceSection(experience));

        List<Experience> education = new ArrayList<>();
        positions.clear();
        positions.add(new Position(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky", null));
        education.add(new Experience("Coursera", "https://www.coursera.org/course/progfun", new ArrayList<>(positions)));
        positions.clear();
        positions.add(new Position(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "\"Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null));
        education.add(new Experience("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", new ArrayList<>(positions)));
        positions.clear();
        positions.add(new Position(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), "3 месяца обучения мобильным IN сетям (Берлин)", null));
        education.add(new Experience("Siemens AG", "http://www.siemens.ru/", new ArrayList<>(positions)));
        positions.clear();
        positions.add(new Position(LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), "6 месяцев обучения цифровым телефонным сетям (Москва)", null));
        education.add(new Experience("Alcatel", "http://www.alcatel.ru/", new ArrayList<>(positions)));
        positions.clear();
        positions.add(new Position(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "Аспирантура (программист С, С++)", null));
        positions.add(new Position(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 3, 1), "Инженер (программист Fortran, C)", null));
        education.add(new Experience("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/", new ArrayList<>(positions)));
        sectionMap.put(SectionType.EDUCATION, new ExperienceSection(education));

        resume.setSections(sectionMap);

        return resume;
    }
}
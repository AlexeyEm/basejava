package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = createResume("uuid1", "Григорий Кислин");
        System.out.println(resume);
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

//        resume.addContact(ContactType.PHONE, "+7(921) 855-0482");
//        resume.addContact(ContactType.SKYPE, "grigory.kislin");
//        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
//        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
//        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
//        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
//        resume.addContact(ContactType.SITE, "http://gkislin.ru/");
//
//        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
//        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
//
//        resume.addSection(SectionType.ACHIEVEMENT,
//                new ListSection(
//                        "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
//                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
//                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
//                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
//                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
//                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
//
//        resume.addSection(SectionType.QUALIFICATIONS,
//                new ListSection(
//                        "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
//                        "Version control: Subversion, Git, Mercury, ClearCase, Perforce",
//                        "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB"));
//
//        resume.addSection(SectionType.EXPERIENCE,
//                new OrganizationSection(
//                        new Organization("Java Online Projects", "http://javaops.ru/",
//                                new Organization.Position(2013, Month.OCTOBER, "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок")),
//                        new Organization("Wrike", "https://www.wrike.com/",
//                                new Organization.Position(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
//                        new Organization("RIT Center", null,
//                                new Organization.Position(2012, Month.APRIL, 2014, Month.OCTOBER, "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python"))
//                ));
//
//        resume.addSection(SectionType.EDUCATION,
//                new OrganizationSection(
//                        new Organization("Coursera", "https://www.coursera.org/course/progfun",
//                                new Organization.Position(2013, Month.MARCH, 2013, Month.MAY, "\"Functional Programming Principles in Scala\" by Martin Odersky", null)),
//                        new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366",
//                                new Organization.Position(2011, Month.MARCH, 2011, Month.APRIL, "\"Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null)),
//                        new Organization("Siemens AG", "http://www.siemens.ru/",
//                                new Organization.Position(2005, Month.JANUARY, 2005, Month.APRIL, "3 месяца обучения мобильным IN сетям (Берлин)", null)),
//                        new Organization("Alcatel", "http://www.alcatel.ru/",
//                                new Organization.Position(1997, Month.SEPTEMBER, 1998, Month.MARCH, "6 месяцев обучения цифровым телефонным сетям (Москва)", null)),
//                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/",
//                                new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура (программист С, С++)", null),
//                                new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.MARCH, "Инженер (программист Fortran, C)", null))
//                ));

        return resume;
    }
}
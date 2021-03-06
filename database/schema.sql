DROP TABLE AppEvent;
DROP TABLE AppActionResult;
DROP TABLE AppAction;
DROP TABLE AppSection;
DROP TABLE AppEventType;
DROP TABLE AppEventSeverity;
DROP TABLE auth;
DROP TABLE Task;

CREATE TABLE auth
(
  authid   SERIAL,
  username VARCHAR(200) NOT NULL UNIQUE,
  hash     VARCHAR(64)  NOT NULL,
  isadmin  BOOLEAN      NOT NULL,
  CONSTRAINT auth_pkey PRIMARY KEY (authid)
);

CREATE TABLE AppEventSeverity
(
  appEventSeverityId SERIAL,
  label              VARCHAR(100) NOT NULL UNIQUE,
  CONSTRAINT app_event_severity_pkey PRIMARY KEY (appEventSeverityId)
);

CREATE TABLE AppEventType
(
  appEventTypeId SERIAL,
  label          VARCHAR(100) NOT NULL UNIQUE,
  CONSTRAINT app_event_type_pkey PRIMARY KEY (appEventTypeId)
);

CREATE TABLE AppSection
(
  appSectionId SERIAL,
  label        VARCHAR(100) NOT NULL UNIQUE,
  CONSTRAINT app_section_pkey PRIMARY KEY (appSectionId)
);

CREATE TABLE AppAction
(
  appActionId SERIAL,
  label       VARCHAR(100) NOT NULL UNIQUE,
  CONSTRAINT app_action_pkey PRIMARY KEY (appActionId)
);

CREATE TABLE AppActionResult
(
  appActionResultId SERIAL,
  label             VARCHAR(100) NOT NULL UNIQUE,
  CONSTRAINT app_action_result_pkey PRIMARY KEY (appActionResultId)
);

CREATE TABLE AppEvent
(
  appEventId   SERIAL,
  timestamp    TIMESTAMP NOT NULL,
  userId       INT       NOT NULL,
  appeventtype     INT       NOT NULL,
  appsection      INT       NOT NULL,
  appaction       INT       NOT NULL,
  appactionResult      INT       NOT NULL,
  appeventseverity     INT       NOT NULL,
  CONSTRAINT app_event_pkey PRIMARY KEY (appEventId),
  CONSTRAINT app_event_fkey_auth_authid        FOREIGN KEY (userId)       REFERENCES Auth             (authid) on delete CASCADE,
  CONSTRAINT app_event_fkey_app_event_type     FOREIGN KEY (appeventtype)     REFERENCES AppEventType     (appEventTypeId),
  CONSTRAINT app_event_fkey_app_section        FOREIGN KEY (appsection)      REFERENCES AppSection       (appSectionId),
  CONSTRAINT app_event_fkey_app_action         FOREIGN KEY (appaction)       REFERENCES AppAction        (appActionId),
  CONSTRAINT app_event_fkey_app_action_result  FOREIGN KEY (appactionResult) REFERENCES AppActionResult  (appActionResultId),
  CONSTRAINT app_event_fkey_app_event_severity FOREIGN KEY (appeventseverity)     REFERENCES AppEventSeverity (appEventSeverityId)
);

CREATE TABLE Task(
  taskId SERIAL,
  userId INT NOT NULL,
  title VARCHAR(1000) NOT NULL,
  description TEXT NOT NULL,
  CONSTRAINT task_pkey PRIMARY KEY (taskId)
);

INSERT INTO Auth (username, hash, isadmin) VALUES ('admin', '$2a$04$5AcVtq0UBOMlcsFXlA3oiO9AG2P2Ex9SAePlSvuBC0t9csI/ZrG7C', TRUE);
INSERT INTO Auth (username, hash, isadmin) VALUES ('nonadmin', '$2a$04$5AcVtq0UBOMlcsFXlA3oiO9AG2P2Ex9SAePlSvuBC0t9csI/ZrG7C', FALSE);

INSERT INTO AppEventSeverity (label) VALUES ('MINOR');
INSERT INTO AppEventSeverity (label) VALUES ('NORMAL');
INSERT INTO AppEventSeverity (label) VALUES ('MAJOR');

INSERT INTO AppEventType (label) VALUES ('AUTH');
INSERT INTO AppEventType (label) VALUES ('APP');

INSERT INTO AppSection (label) VALUES ('LOGIN');
INSERT INTO AppSection (label) VALUES ('ADMIN');
INSERT INTO AppSection (label) VALUES ('TASKS');

INSERT INTO AppAction (label) VALUES ('LIST_USERS');
INSERT INTO AppAction (label) VALUES ('USER_LOGIN');
INSERT INTO AppAction (label) VALUES ('USER_LOGOUT');
INSERT INTO AppAction (label) VALUES ('CLEAR_EVENT_LOG');
INSERT INTO AppAction (label) VALUES ('DELETE_USER');
INSERT INTO AppAction (label) VALUES ('CREATE_USER');
INSERT INTO AppAction (label) VALUES ('CREATE_TASK');

INSERT INTO AppActionResult (label) VALUES ('ACTION_SUCCESS');
INSERT INTO AppActionResult (label) VALUES ('ACTION_FAILURE');
INSERT INTO AppActionResult (label) VALUES ('ACTION_NORMAL');

INSERT INTO public.appevent (timestamp, userid, appeventtype, appsection, appaction, appactionresult, appeventseverity) VALUES ( '2016-07-12 10:24:18.125000', 1, 1, 1, 2, 1, 3);
INSERT INTO public.appevent (timestamp, userid, appeventtype, appsection, appaction, appactionresult, appeventseverity) VALUES ('2016-07-12 11:43:40.989000', 1, 1, 1, 2, 2, 1);
INSERT INTO public.appevent (timestamp, userid, appeventtype, appsection, appaction, appactionresult, appeventseverity) VALUES ('2016-07-12 11:43:45.147000', 2, 2, 2, 1, 2, 2);

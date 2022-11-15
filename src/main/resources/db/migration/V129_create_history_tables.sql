CREATE EXTENSION IF NOT EXISTS pgcrypto;
CREATE SCHEMA IF NOT EXISTS analytics;

CREATE TABLE analytics.action_history (
                              id uuid NOT NULL DEFAULT public.gen_random_uuid() PRIMARY KEY,
                              environment character varying(255) NOT NULL,
                              additional_info character varying(255),
                              device character varying(255) NOT NULL

);

CREATE TABLE analytics.user_data (
                              id uuid NOT NULL DEFAULT public.gen_random_uuid() PRIMARY KEY,
                              username character varying(255) NOT NULL,
                              email character varying(255) NULL

);

CREATE TABLE analytics.user_action (
                                id uuid NOT NULL DEFAULT public.gen_random_uuid() PRIMARY KEY,
                                action_type character varying(255) NOT NULL,
                                action_date timestamp with time zone NOT NULL,
                                URI character varying(255) NOT NULL,
                                ip character varying(255) NOT NULL,
                                action_history_id uuid NOT NULL,
                                user_data_id uuid NOT NULL,
                                CONSTRAINT fk_action_history
                                  FOREIGN KEY(action_history_id)
                                  REFERENCES action_history(id),
                                 CONSTRAINT fk_user_data
                                  FOREIGN KEY(user_data_id)
                                  REFERENCES user_data(id)
);


CREATE TABLE analytics.history (
                            id uuid NOT NULL DEFAULT public.gen_random_uuid() PRIMARY KEY,
                            environment character varying(255) NOT NULL,
                            action_type character varying(255) NOT NULL,
                            action_date timestamp with time zone NOT NULL,
                            additional_info character varying(255),
                            device character varying(255) NOT NULL,
                            username character varying(255) NOT NULL,
                            email character varying(255) NULL,
                            URI character varying(255) NOT NULL,
                            ip character varying(255) NOT NULL
);
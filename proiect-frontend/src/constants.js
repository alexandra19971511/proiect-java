export const ROOT_ROUTE = "/";
export const LOGIN_ROUTE = "/login";
export const SIGNUP_ROUTE = "/signup";
export const MAIN_ROUTE = "/main";
export const DASHBOARD_ROUTE = `${MAIN_ROUTE}/dashboard`;
export const FORMS_ROUTE = `${MAIN_ROUTE}/forms`;
export const QUIZ_ROUTE = `${MAIN_ROUTE}/forms/quiz`;
export const INFO_ROUTE = `${MAIN_ROUTE}/info`;
export const RESTART_QUIZ_ROUTE = `${MAIN_ROUTE}/restart/quiz`;
export const ADMIN_ROUTE = `${MAIN_ROUTE}/admin`;

export const axios = require('axios');
export const SERVER_BASE_URL = 'http://localhost:8081'

export const ADMIN_ROLE = 'ROLE_ADMIN';
export const USER_ID = 'userId';
export const USER_ROLE = 'userRole';
export const USER_EMAIL = 'email';
export const TOKEN = 'token';
export const SELECTED_PROFESSOR_ID = 'professorId';
export const SELECTED_COURSE_ID = 'courseId';
export const IS_LOGGED_IN = 'isLoggedIn';
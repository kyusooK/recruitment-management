
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ResumeResumeManager from "./components/listers/ResumeResumeCards"
import ResumeResumeDetail from "./components/listers/ResumeResumeDetail"

import InterviewInterviewManager from "./components/listers/InterviewInterviewCards"
import InterviewInterviewDetail from "./components/listers/InterviewInterviewDetail"

import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import ReportApplicationAnalyzeDataManager from "./components/listers/ReportApplicationAnalyzeDataCards"
import ReportApplicationAnalyzeDataDetail from "./components/listers/ReportApplicationAnalyzeDataDetail"

import ReportView from "./components/ReportView"
import ReportViewDetail from "./components/ReportViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/resumes/resumes',
                name: 'ResumeResumeManager',
                component: ResumeResumeManager
            },
            {
                path: '/resumes/resumes/:id',
                name: 'ResumeResumeDetail',
                component: ResumeResumeDetail
            },

            {
                path: '/interviews/interviews',
                name: 'InterviewInterviewManager',
                component: InterviewInterviewManager
            },
            {
                path: '/interviews/interviews/:id',
                name: 'InterviewInterviewDetail',
                component: InterviewInterviewDetail
            },

            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/reports/applicationAnalyzeData',
                name: 'ReportApplicationAnalyzeDataManager',
                component: ReportApplicationAnalyzeDataManager
            },
            {
                path: '/reports/applicationAnalyzeData/:id',
                name: 'ReportApplicationAnalyzeDataDetail',
                component: ReportApplicationAnalyzeDataDetail
            },

            {
                path: '/reports/reports',
                name: 'ReportView',
                component: ReportView
            },
            {
                path: '/reports/reports/:id',
                name: 'ReportViewDetail',
                component: ReportViewDetail
            },


    ]
})

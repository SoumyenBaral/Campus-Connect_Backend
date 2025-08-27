import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Admin } from './admin/admin';
import { Host } from './host/host';
import { Coordinator } from './coordinator/coordinator';
import { Student } from './student/student';
import { ContactUs } from './contact-us/contact-us';
import { Login } from './login/login';
import { Helpcenter } from './helpcenter/helpcenter';
import { HomeGallery } from './home-gallery/home-gallery';
import { OngoingEvents } from './ongoing-events/ongoing-events';
import { UpcomingEvents } from './upcoming-events/upcoming-events';
import { RegisterPage } from './register-page/register-page';

export const routes: Routes = [

    {
        path:'',
        component:Home,
    },
    {
        path:'home',
        component:Home,
    },
    {
        path:'admin',
        component:Admin,
    },
    {
        path:'coordinator',
        component:Coordinator,
    },
    {
        path:'student',
        component:Student,
    },
    {
        path:'host',
        component:Host,
    },
    {
        path:'contact-us',
        component:ContactUs,
    },
    {
        path:'login',
        component:Login,
    },
    {
        path:'helpcenter',
        component:Helpcenter,
    }, 
    {
        path:'gallery',
        component:HomeGallery,
    },
    {
        path:'Ongoing-Events',
        component:OngoingEvents,

    },
    {
        path:'Upcoming-Events',
        component:UpcomingEvents,
    },
    {
        path:'register',
        component:RegisterPage,
    }
];

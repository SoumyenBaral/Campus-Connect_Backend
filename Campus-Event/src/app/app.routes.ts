import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Admin } from './admin/admin';
import { Host } from './host/host';
import { Coordinator } from './coordinator/coordinator';
import { Student } from './student/student';
import { ContactUs } from './contact-us/contact-us';
import { Login } from './login/login';
import { Profile } from './profile/profile';
import { Helpcenter } from './helpcenter/helpcenter';
import { Navbar } from './navbar/navbar';

export const routes: Routes = [

    {
        path:'',
        redirectTo:'home',
        pathMatch:'full',
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
        path:'profile',
        component:Profile,
    },
    {
        path:'helpcenter',
        component:Helpcenter,
    },
    
];

import { RouterModule, Routes } from '@angular/router';
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
import { AdminGuide } from './admin-guide/admin-guide';
import { CoordinatorGuide } from './coordinator-guide/coordinator-guide';
import { UserGuide } from './user-guide/user-guide';
import { HostGuide } from './host-guide/host-guide';
import { DanceGallery } from './dance-gallery/dance-gallery';
import { MusicGallery } from './music-gallery/music-gallery';
import { TechGallery } from './tech-gallery/tech-gallery';
import { SignUp } from './signup/signup';
import { NgModule } from '@angular/core';


export const routes: Routes = [
// { path: 'login', component: Login },
//   { path: '', redirectTo: '/login', pathMatch: 'full' }, // <-- This is the redirect
    {
        path: '',
        component: Home,
    },
    {
        path: 'home',
        component: Home,
    },
    {
        path: 'admin',
        component: Admin,
    },
    {
        path: 'coordinator',
        component: Coordinator,
    },
    {
        path: 'student',
        component: Student,
    },
    {
        path: 'host',
        component: Host,
    },
    {
        path: 'contact-us',
        component: ContactUs,
    },
    {
        path: 'SignUp',
        component: SignUp,
    },
    {
        path: 'helpcenter',
        component: Helpcenter,
    },
    {
        path: 'gallery',
        component: HomeGallery,
    },
    {
        path: 'Ongoing-Events',
        component: OngoingEvents,

    },
    {
        path: 'Upcoming-Events',
        component: UpcomingEvents,
    },
    {
        path: 'register',
        component: RegisterPage,
    },
    {
        path: 'admin-manual',
        component: AdminGuide,
    },
    {
        path: 'coordinator-manual',
        component: CoordinatorGuide,
    },
    {
        path: 'user-manual',
        component: UserGuide,
    },
    {
        path: 'host-manual',
        component: HostGuide,
    },
    {
        path:'dance-gallery',
        component:DanceGallery,
    },
    {
        path:'music-gallery',
        component:MusicGallery,
    },
    {
        path:'tech-gallery',
        component:TechGallery,
    },
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
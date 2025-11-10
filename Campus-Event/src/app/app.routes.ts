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
import { AuthGuard } from './guards/auth-guard';
import { RoleGuard } from './guards/role/role-guard';
import { CreateEvent } from './create-event/create-event';
import { Feedback } from './feedback/feedback';
import { HostDetails } from './host-details/host-details';
import { CoordinatorDetails } from './coordinator-details/coordinator-details';
import { StudentDetails } from './student-details/student-details';
import { EventDetails } from './event-details/event-details';
import { Allevents } from './allevents/allevents';



export const routes: Routes = [

    {
        path: '',
        component: Home,
        pathMatch: 'full',
    },
    {
        path: 'home',
        component: Home,
    },
    {
        path: 'admin',
        component: Admin,
        canActivate: [RoleGuard],
        data: { expectedRole: 'ADMIN' },
        children: [
            { path: 'host-details', component: HostDetails },
            { path: 'coordinator-details', component: CoordinatorDetails },
            { path: 'student-details', component: StudentDetails },
            { path: 'event-details', component: EventDetails },
        ]
    },
    
    {
        path: 'coordinator',
        component: Coordinator,
        canActivate: [RoleGuard],
        data: { expectedRole: 'COORDINATOR' }
    },
    {
        path: 'student',
        component: Student,
        canActivate: [AuthGuard],
        data: { expectedRole: 'STUDENT' }
    },
    {
        path: 'host',
        component: Host,
        canActivate: [RoleGuard],
        data: { expectedRole: 'HOST' }
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
        path: 'register',
        component: RegisterPage,
    },
    {
        path: 'feedback',
        component: Feedback,
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
        path: 'dance-gallery',
        component: DanceGallery,
    },
    {
        path: 'music-gallery',
        component: MusicGallery,
    },
    {
        path: 'tech-gallery',
        component: TechGallery,
    },
    {
        path: 'login',
        component: Login
    },
    {
        path: 'event',
        component: CreateEvent
    },
    {
        path: 'allevents',
        component: Allevents
    },
    {
        path:'hostdetails',
        component:HostDetails
    }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }
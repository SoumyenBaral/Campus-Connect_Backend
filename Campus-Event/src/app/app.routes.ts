import { Routes } from '@angular/router';

// 💡 VERIFICATION: Assuming all components are standalone and imported from their respective folders.
// The file path assumes a flat structure inside 'src/app' or an alias setup. 
// If your components are named Home.ts, Admin.ts, etc., please rename them to HomeComponent, AdminComponent, etc., for best practice.

import { Home } from './home/home';
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
import { AuthGuard } from './guards/auth-guard';
import { RoleGuard } from './guards/role/role-guard';
import { CreateEvent } from './create-event/create-event';
import { AdminComponent } from './admin/admin';
// 💡 ADDITION: Import a component for 404 handling
// 💡 ADDITION: Import for routing module and decorator (already present but good to confirm)
// import { RouterModule, Routes } from '@angular/router'; 
import { NgModule } from '@angular/core'; 


export const routes: Routes = [
    // Default Path: Redirects to /home or loads Home component
    {
        path: '',
        component: Home,
        pathMatch:'full',
    },
    {
        path: 'home',
        component: Home,
    },

    // --- Protected Routes (Role-Based) ---
    {
        path: 'admin',
        component: AdminComponent,
        canActivate: [RoleGuard],
        data: { expectedRole: 'ADMIN' },
    },
    {
        path: 'coordinator',
        component: Coordinator,
        canActivate: [RoleGuard],
        data: { expectedRole: 'COORDINATOR' }
    },
    // Note: Student should ideally use RoleGuard like others for consistency, 
    // but keeping AuthGuard as provided in original code.
    {
        path: 'student',
        component: Student,
        canActivate: [AuthGuard], // Assuming AuthGuard checks for logged-in status
        // data: { expectedRole: 'STUDENT' } // Role check is redundant if AuthGuard is sufficient for general access
    },
    {
        path: 'host',
        component: Host,
        canActivate: [RoleGuard],
        data: { expectedRole: 'HOST' }
    },
    {
        path:'event', // Route for creating events
        component:CreateEvent,
        canActivate: [AuthGuard] // Only logged-in users can create events
    },

    // --- Public Routes ---
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
    {
        path:'login',
        component:Login
    }
    
];

// Note: If you are using Angular 17+ and the functional router setup in main.ts, 
// you do not need the NgModule and AppRoutingModule class wrapper. 
// However, since your file includes it, I'll assume you are using the module-based setup.
// If using standalone components/functional routing, you would remove the @NgModule block.
/*
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
*/

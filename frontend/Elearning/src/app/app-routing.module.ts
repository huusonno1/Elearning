import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { SignupComponent } from './components/signup/signup.component';
import { CourseSearchComponent } from './components/course-search/course-search.component';
import { CourseDetailBfBuyComponent } from './components/course-detail-bf-buy/course-detail-bf-buy.component';
import { CourseDetailAfBuyComponent } from './components/course-detail-af-buy/course-detail-af-buy.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'course-search', component: CourseSearchComponent },
  { path: 'course-detail-bf-buy', component: CourseDetailBfBuyComponent },
  { path: 'course-detail-af-buy', component: CourseDetailAfBuyComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

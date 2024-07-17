import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { SignupComponent } from './components/signup/signup.component';
import { CarouselCourseComponent } from './components/carousel-course/carousel-course.component';
import { CourseSearchComponent } from './components/course-search/course-search.component';
import { FpCourseListComponent } from './components/fp-course-list/fp-course-list.component';
import { FpSidebarComponent } from './components/fp-sidebar/fp-sidebar.component';
import { CoursePaginationComponent } from './components/course-pagination/course-pagination.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    HomeComponent,
    FooterComponent,
    SignupComponent,
    CarouselCourseComponent,
    CourseSearchComponent,
    FpCourseListComponent,
    FpSidebarComponent,
    CoursePaginationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CarouselModule,
    NgbModule,
    BrowserAnimationsModule
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent, HeaderComponent]
})
export class AppModule { }

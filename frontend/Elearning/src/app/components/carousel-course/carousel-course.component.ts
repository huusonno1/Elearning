import { Component, ElementRef, ViewChild, AfterViewInit, Renderer2 } from '@angular/core';



@Component({
  selector: 'app-carousel-course',
  templateUrl: './carousel-course.component.html',
  styleUrls: ['./carousel-course.component.scss'],
  
})
export class CarouselCourseComponent implements AfterViewInit{
  @ViewChild('carouselInner') carouselInner!: ElementRef;
  cardWidth = 0;
  scrollPosition = 0;
  coursesPerPage = 3;

  constructor(private renderer: Renderer2) { }
  
  ngAfterViewInit() {
    const cardElement = this.carouselInner.nativeElement.querySelector('.carousel-item');
    if (cardElement) {
      this.cardWidth = cardElement.getBoundingClientRect().width;
    }
  }

  prev() {
    console.log(this.scrollPosition);
    if (this.scrollPosition > 0) {
      this.scrollPosition -= this.cardWidth;
      this.smoothScroll(this.scrollPosition);
    }
  }

  next() {
    const carouselWidth = this.carouselInner.nativeElement.scrollWidth;

    console.log(this.scrollPosition, carouselWidth, this.cardWidth);
    if (this.scrollPosition < (carouselWidth - this.cardWidth*5) ) {
      this.scrollPosition += this.cardWidth;
      this.smoothScroll(this.scrollPosition);
    }
    console.log(this.scrollPosition, carouselWidth, this.cardWidth);

  }

  getStarIcons(rating: number): { class: string }[] {
    const totalStars = 5;
    const fullStars = Math.floor(rating);
    const halfStar = rating - fullStars >= 0.5 ? 1 : 0;
    const emptyStars = totalStars - fullStars - halfStar;

    let stars: { class: string }[] = [];

    for (let i = 0; i < fullStars; i++) {
        stars.push({ class: 'fas fa-star text-warning' }); // Màu vàng
    }

    if (halfStar === 1) {
        stars.push({ class: 'fas fa-star-half-alt text-warning' }); // Nửa sao màu vàng
    }

    for (let i = 0; i < emptyStars; i++) {
        stars.push({ class: 'far fa-star text-warning' }); // Màu không đầy (trống) màu vàng
    }

    return stars;
  }


  private smoothScroll(targetScrollPosition: number) {
    const startPosition = this.carouselInner.nativeElement.scrollLeft;
    const distance = targetScrollPosition - startPosition;
    const duration = 600;
    let startTime: number | null = null;

    const animateScroll = (currentTime: number) => {
      if (startTime === null) startTime = currentTime;
      const timeElapsed = currentTime - startTime;
      const scrollAmount = this.easeInOutQuad(timeElapsed, startPosition, distance, duration);
      this.renderer.setProperty(this.carouselInner.nativeElement, 'scrollLeft', scrollAmount);

      if (timeElapsed < duration) {
        requestAnimationFrame(animateScroll);
      } else {
        this.renderer.setProperty(this.carouselInner.nativeElement, 'scrollLeft', targetScrollPosition);
      }
    };

    requestAnimationFrame(animateScroll);
  }

  private easeInOutQuad(t: number, b: number, c: number, d: number): number {
    t /= d / 2;
    if (t < 1) return c / 2 * t * t + b;
    t--;
    return -c / 2 * (t * (t - 2) - 1) + b;
  }


  courses = [
    { id: 1, title: 'Course 1', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 4.5 },
    { id: 2, title: 'Course 2', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 3.5 },
    { id: 3, title: 'Course 3', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 1.5 },
    { id: 4, title: 'Course 4', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 2.5 },
    { id: 4, title: 'Course 5', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 4 },
    { id: 4, title: 'Course 6', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 5 },
    { id: 4, title: 'Course 7', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 0 },
    { id: 4, title: 'Course 8', image: 'real.jpg', teacher: 'giáo viên 1', price: '100.000 vnđ', description: 'đây là khóa học hay về A B c gì đó mà bạn nên mua với giá cao cắt cổ', rating: 1 },

    // Add more courses as needed
  ];



}

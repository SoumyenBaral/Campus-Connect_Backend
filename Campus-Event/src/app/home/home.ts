
import { Component, AfterViewInit } from '@angular/core';
import { Footer } from "../footer/footer";
import { Navbar } from "../navbar/navbar";
import { RouterOutlet, RouterLink } from '@angular/router';
import { CreateEvent } from '../create-event/create-event';
import { SignUp } from '../signup/signup';
import { Coordinator } from '../coordinator/coordinator';
import { Host } from "../host/host";

@Component({
  selector: 'app-home',
  imports: [
    Footer,
    Navbar,
    CreateEvent,
    RouterOutlet,
    SignUp,
    Coordinator,
    Host,
    RouterLink
],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements AfterViewInit {

ngAfterViewInit(): void {
    // Wrap in setTimeout to ensure Angular has fully rendered the DOM 
    // and resolved all data bindings ([attr.data-target]) before querying.
    setTimeout(() => {
      this.scrollReveal();
      this.animateStatistics();
    }, 100); 
  }

  // ==========================
  // Scroll Reveal Animation
  // ==========================
  scrollReveal() {
    const reveals = document.querySelectorAll('.reveal');
    if (!reveals.length) return; // Safety check

    const observer = new IntersectionObserver((entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          (entry.target as HTMLElement).classList.add('active');
          observer.unobserve(entry.target);
        }
      });
    }, {
      threshold: 0.15
    });

    reveals.forEach(reveal => observer.observe(reveal));
  }

  // ==========================
  // Statistics Counter
  // ==========================
  animateStatistics() {
    const counters = document.querySelectorAll('.stat-number');
    if (!counters.length) return; // Safety check
    
    const speed = 100;

    const observer = new IntersectionObserver((entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          const counter = entry.target as HTMLElement;
          
          const updateCount = () => {
            // Safely grab the target. If it's missing, default to 0.
            const targetAttr = counter.getAttribute('data-target');
            const target = targetAttr ? Number(targetAttr) : 0;
            
            // If target is 0, don't run the animation loop
            if (target === 0) {
                counter.innerText = '0';
                return;
            }

            const countText = counter.innerText.replace(/,/g, '');
            const count = countText ? Number(countText) : 0;

            const inc = target / speed;

            if (count < target) {
              counter.innerText = Math.ceil(count + inc).toLocaleString();
              // Use requestAnimationFrame for smoother browser performance instead of setTimeout
              requestAnimationFrame(updateCount);
            } else {
              counter.innerText = target.toLocaleString();
            }
          };

          updateCount();
          observer.unobserve(counter);
        }
      });
    }, {
      threshold: 0.5
    });

    counters.forEach(counter => observer.observe(counter));
  }
}
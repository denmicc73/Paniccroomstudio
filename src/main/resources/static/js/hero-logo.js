 document.addEventListener('DOMContentLoaded', () => {
    const heroLogo = document.querySelector('.hero-logo');

    if (!heroLogo) return;

    let clickCount = 0;
    let clickTimer = null;
    let isFlying = false;

    heroLogo.addEventListener('click', () => {
      if (isFlying) return;

      heroLogo.classList.remove('is-spinning');
      void heroLogo.offsetWidth;
      heroLogo.classList.add('is-spinning');

      clickCount++;

      clearTimeout(clickTimer);
      clickTimer = setTimeout(() => {
        clickCount = 0;
      }, 350);

      if (clickCount >= 3) {
        isFlying = true;
        clickCount = 0;

        heroLogo.classList.remove('is-spinning');
        void heroLogo.offsetWidth;
        heroLogo.classList.add('is-flying');
      }
    });

    heroLogo.addEventListener('animationend', (e) => {
      if (e.animationName === 'heroLogoSpin') {
        heroLogo.classList.remove('is-spinning');
      }

      if (e.animationName === 'heroLogoFly') {
        heroLogo.classList.remove('is-flying');
        heroLogo.style.opacity = '1';
        heroLogo.style.transform = 'none';
        isFlying = false;
      }
    });
  });
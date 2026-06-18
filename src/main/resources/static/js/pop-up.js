document.addEventListener('DOMContentLoaded', () => {
  const banner = document.getElementById('cookieBanner');
  const acceptBtn = document.getElementById('acceptCookies');
  const rejectBtn = document.getElementById('rejectCookies');

  if (!banner || !acceptBtn || !rejectBtn) return;

  const consent = localStorage.getItem('cookie-consent');

  if (consent === 'accepted' || consent === 'rejected') {
    banner.classList.add('is-hidden');

    if (consent === 'accepted') {
      enableOptionalCookies();
    }

    return;
  }

  acceptBtn.addEventListener('click', () => {
    localStorage.setItem('cookie-consent', 'accepted');
    banner.classList.add('is-hidden');
    enableOptionalCookies();
  });

  rejectBtn.addEventListener('click', () => {
    localStorage.setItem('cookie-consent', 'rejected');
    banner.classList.add('is-hidden');
  });

  function enableOptionalCookies() {
    console.log('Activar aquí analytics, píxeles o scripts opcionales.');
  }
});
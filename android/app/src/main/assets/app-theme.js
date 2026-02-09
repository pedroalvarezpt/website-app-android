/**
 * PlantaLivre App Theme
 * JavaScript customizations injected into WebView
 */

(function() {
  'use strict';

  window.PLANTALIVRE_APP = true;
  window.PLANTALIVRE_PLATFORM = 'android';

  console.log('[PlantaLivre App] Android theme injected');

  const hideInApp = () => {
    const selectors = [];
    selectors.forEach(selector => {
      const elements = document.querySelectorAll(selector);
      elements.forEach(el => el.style.display = 'none');
    });
  };

  const injectStyles = () => {
    const style = document.createElement('style');
    style.textContent = `
      body { overscroll-behavior: none; }
      .app-header { padding-top: env(safe-area-inset-top); }
      .app-footer { padding-bottom: env(safe-area-inset-bottom); }
      ::-webkit-scrollbar { width: 0px; background: transparent; }
      
      /* TEST: Opacity on .image class */
      .image {
        opacity: 0.5 !important;
      }
    `;
    document.head.appendChild(style);
    console.log('[PlantaLivre App] CSS injected - .image opacity set to 0.5');
  };

  const hasBridge = typeof window.AndroidBridge !== 'undefined';
  
  if (hasBridge) {
    console.log('[PlantaLivre App] AndroidBridge available');
    try {
      const appVersion = window.AndroidBridge.getAppVersion();
      console.log('[PlantaLivre App] Version:', appVersion);
    } catch (e) {
      console.warn('[PlantaLivre App] Could not get app version', e);
    }
  }

  window.PlantaLivreApp = {
    isApp: true,
    platform: 'android',
    
    openExternal: (url) => {
      if (window.AndroidBridge) {
        window.AndroidBridge.openExternal(url);
      } else {
        window.open(url, '_blank');
      }
    },

    share: (title, text, url) => {
      if (window.AndroidBridge) {
        window.AndroidBridge.share(title, text, url);
      } else if (navigator.share) {
        navigator.share({ title, text, url });
      }
    },

    toast: (message) => {
      if (window.AndroidBridge) {
        window.AndroidBridge.showToast(message);
      }
    },

    getVersion: () => {
      if (window.AndroidBridge) {
        return window.AndroidBridge.getAppVersion();
      }
      return '1.0.0';
    }
  };

  const interceptExternalLinks = () => {
    document.addEventListener('click', (e) => {
      const target = e.target.closest('a');
      if (!target) return;
      const href = target.getAttribute('href');
      if (!href) return;
      if (href.startsWith('http') && !href.includes('plantalivre.pt')) {
        e.preventDefault();
        window.PlantaLivreApp.openExternal(href);
      }
    }, true);
  };

  const initialize = () => {
    hideInApp();
    injectStyles();
    interceptExternalLinks();
    console.log('[PlantaLivre App] Initialization complete');
  };

  if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initialize);
  } else {
    initialize();
  }

})();

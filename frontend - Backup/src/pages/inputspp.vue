<template>
  <q-page>
<div
class="judul text-h6 q-pa-md bg-grey-2 q-mb-md">
        <q-toolbar-title >
          <!-- Mohon Input Data Guru -->
        </q-toolbar-title>
<div class="komentar top-left q-pt-md q-pr-md">
<q-icon name="money" />
  SPP
</div>
</div>
    <div class="row justify-center q-pt-md">
      <div class="col-md-5 col-xs-8">
        <q-card>
          <q-card-section>
            <div class="text-h5 q-pb-sm text-center">
              <b>INPUT SPP</b>
            </div>
            <q-form @submit="onSubmit" @reset="onReset" class="q-gutter-sm">

              <q-input
                filled
                v-model="nis"
                label="NIS *"
                mask="########"
                lazy-rules
                :rules="[ val => val !== null && val !== '' || 'Please type Your NIS']"
              />

              <q-input
                filled
                v-model="nama"
                label="Nama *"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type Your Name']"
              />

              <q-input
                filled
                v-model="spp_bulan"
                label="SPP Bulan *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type  Month']"
              />

              <q-input
              filled
              v-model="status"
              label="Status"
              lazy-rules
              :rules="[ val => val !== '' && val !== null || 'Please type Status']"
              />

              <!-- <q-input
                filled
                v-model="kodemk"
                label="Course Code *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Course Code']"
              />

              <q-input
                filled
                v-model="namamk"
                label="Courses *"
                lazy-rules
                :rules="[ val => val !== '' && val !== null || 'Please type Your Courses']"
              /> -->

              <div>
                <q-btn label="Add +" type="submit" color="light-green-14" class="q-mt-md" />
                <q-btn label="Reset" type="reset" color="indigo-9" flat class="q-ml-sm q-mt-md" />
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
export default {
  data () {
    return {
      nis: '',
      nama: '',
      spp_bulan: '',
      status: ''
    }
  },
  methods: {
    onSubmit () {
      this.$axios.post('spp/simpan', {
        nis: this.nis,
        nama: this.nama,
        spp_bulan: this.spp_bulan,
        status: this.status
      }).then(res => {
        if (res.data.error) {
          this.showNotif(res.data.pesan, 'negative')
        } else {
          this.showNotif(res.data.pesan, 'Positive')
          this.onReset()
        }
      })
    },
    onReset () {
      this.nis = ''
      this.nama = ''
      this.spp_bulan = ''
      this.status = ''
    },
    showNotif (msg, color) {
      this.$q.notify({
        message: msg,
        color: color
      })
    }
  }
}
</script>
